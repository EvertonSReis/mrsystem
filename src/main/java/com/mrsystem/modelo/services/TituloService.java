package com.mrsystem.modelo.services;

import static java.lang.String.format;

import com.mrsystem.dtos.listagem.ListagemDTO;
import com.mrsystem.dtos.request.titulo.cadastro.CadastroTituloDTO;
import com.mrsystem.dtos.response.PaginacaoDTO;
import com.mrsystem.dtos.response.titulo.ListagemTituloRetornoDTO;
import com.mrsystem.dtos.response.titulo.TituloRetornoDTO;
import com.mrsystem.excecoes.MRSystemRuntimeException;
import com.mrsystem.excecoes.ValidationException;
import com.mrsystem.modelo.builders.TituloBuilder;
import com.mrsystem.modelo.entitys.FormaPagamento;
import com.mrsystem.modelo.entitys.Pessoa;
import com.mrsystem.modelo.entitys.Titulo;
import com.mrsystem.modelo.enums.EValidacao;
import com.mrsystem.modelo.repository.FormaPagamentoRepository;
import com.mrsystem.modelo.repository.PessoaRepository;
import com.mrsystem.modelo.repository.TituloRepository;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TituloService {

    @Autowired TituloRepository tituloRepository;

    @Autowired PessoaRepository pessoaRepository;

    @Autowired FormaPagamentoRepository formaPagamentoRepository;

    @Autowired TituloBuilder tituloBuilder;

    public TituloRetornoDTO cadastrar(CadastroTituloDTO tituloDTO) {
        try {
            Pessoa pessoa = obterPessoa(tituloDTO.getPessoa().getIdPessoa());

            FormaPagamento formaPagamento =
                    obterFormaPagamento(tituloDTO.getFormaPagamento().getIdFormaPagamento());

            Titulo titulo =
                    tituloBuilder.parserCadastroTitulo(
                            tituloDTO,
                            pessoa,
                            formaPagamento,
                            gerarNumeroTitulo(),
                            valorTituloAtualizado(tituloDTO));

            return tituloBuilder.builderRetornoTitulo(tituloRepository.save(titulo));
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception e) {
            log.error(format("Ocorreu um erro ao cadastrar o titulo."), e);
            throw new MRSystemRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }

    private Pessoa obterPessoa(UUID idPessoa) {
        return pessoaRepository
                .findById(idPessoa)
                .orElseThrow(() -> new ValidationException(EValidacao.PESSOA_NAO_ENCONTRADA));
    }

    private FormaPagamento obterFormaPagamento(UUID idFormaPagamento) {
        return formaPagamentoRepository
                .findById(idFormaPagamento)
                .orElseThrow(
                        () -> new ValidationException(EValidacao.FORMA_PAGAMENTO_NAO_ENCONTRADA));
    }

    private Integer gerarNumeroTitulo() {
        Integer numeroUltimoTitulo = tituloRepository.numeroUltimoTitulo();
        if (numeroUltimoTitulo != null) {
            return numeroUltimoTitulo + 1;
        }
        return 1;
    }

    private BigDecimal valorTituloAtualizado(CadastroTituloDTO tituloDTO) {
        if (tituloDTO.getDataPagamento() == null) {
            return tituloDTO.getValorTitulo();
        }

        return tituloDTO
                .getValorTitulo()
                .add(tituloDTO.getValorJuros())
                .add(tituloDTO.getValorMulta());
    }

    public ListagemTituloRetornoDTO retornarTodosTitulos(ListagemDTO listagemDTO) {
        try {
            Page<Titulo> titulos = obterPaginacaoTitulo(listagemDTO);

            ListagemTituloRetornoDTO retorno = new ListagemTituloRetornoDTO();
            if (titulos.getContent().size() > 0) {
                retorno.setPaginacao(
                        new PaginacaoDTO(
                                listagemDTO.getNumeroPagina(),
                                listagemDTO.getItensPorPagina(),
                                titulos.getTotalPages(),
                                titulos.getTotalElements()));
                titulos.getContent()
                        .forEach(
                                titulo ->
                                        retorno.getTitulos()
                                                .add(tituloBuilder.builderRetornoTitulo(titulo)));
            }

            return retorno;
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception e) {
            log.error(format("Ocorreu um erro ao retornar listagem dos titulos"), e);
            throw new MRSystemRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }

    private Page<Titulo> obterPaginacaoTitulo(ListagemDTO listagemDTO) {
        Pageable pageable =
                PageRequest.of(
                        listagemDTO.getNumeroPagina() > 0 ? (listagemDTO.getNumeroPagina() - 1) : 0,
                        listagemDTO.getItensPorPagina(),
                        Sort.by(
                                listagemDTO.getTipoOrdenacao().ordenacao,
                                listagemDTO.getOrdenarPor().getNome()));

        return tituloRepository.findAll(pageable);
    }
}
