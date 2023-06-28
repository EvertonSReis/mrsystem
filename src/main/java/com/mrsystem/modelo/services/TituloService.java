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
import com.mrsystem.modelo.enums.ESituacaoTitulo;
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

            ESituacaoTitulo situacaoTitulo = obterSituacaoTitulo(tituloDTO);

            Titulo titulo =
                    tituloBuilder.parserCadastroTitulo(
                            tituloDTO,
                            pessoa,
                            formaPagamento,
                            gerarNumeroTitulo(),
                            valorTituloAtualizado(tituloDTO),
                            situacaoTitulo);

            return tituloBuilder.builderRetornoTitulo(tituloRepository.save(titulo));
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception e) {
            log.error(format("Ocorreu um erro ao cadastrar o titulo."), e);
            throw new MRSystemRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }

    public void atualizarPagamento(UUID idTitulo, CadastroTituloDTO tituloDTO) {
        try {
            if (tituloDTO.getDataPagamento() == null)
                throw new ValidationException(EValidacao.DATA_INVALIDA);
            Titulo titulo = buscarTituloPorId(idTitulo);

            CadastroTituloDTO cadastroTituloDTO = tituloBuilder.tituloDTO(tituloDTO, titulo);
            FormaPagamento pagamento =
                    obterFormaPagamento(tituloDTO.getFormaPagamento().getIdFormaPagamento());
            BigDecimal valorAtualizado = valorTituloAtualizado(tituloDTO);
            ESituacaoTitulo situacaoTitulo = obterSituacaoTitulo(tituloDTO);
            tituloRepository.save(
                    tituloBuilder.parserPagamentoTitulo(
                            cadastroTituloDTO, pagamento, valorAtualizado, situacaoTitulo, titulo));
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception e) {
            log.error(format("Ocorreu um erro ao informar pagamento do titulo."), e);
            throw new MRSystemRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
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

    public TituloRetornoDTO retornarPorId(UUID idTitulo) {
        try {
            Titulo titulo = buscarTituloPorId(idTitulo);

            return tituloBuilder.builderRetornoTitulo(titulo);
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception e) {
            log.error(format("Ocorreu um erro ao retornar o titulo por id."), e);
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

    private ESituacaoTitulo obterSituacaoTitulo(CadastroTituloDTO tituloDTO) {

        if (tituloDTO.getDataVencimento().isBefore(tituloDTO.getDataEmissão())) {
            throw new ValidationException(EValidacao.DATA_VENCIMENTO_ANTERIOR_EMISSAO);
        } else if (tituloDTO.getDataPagamento() != null
                && tituloDTO.getDataPagamento().isBefore(tituloDTO.getDataEmissão())) {
            throw new ValidationException(EValidacao.DATA_PAGAMENTO_ANTERIOR_EMISSAO);
        } else if (tituloDTO.getDataPagamento() != null) {
            return ESituacaoTitulo.PAGO;
        } else {
            return ESituacaoTitulo.ABERTO;
        }
    }

    private Titulo buscarTituloPorId(UUID idTitulo){
        if(idTitulo == null) throw new ValidationException(EValidacao.ID_TITULO_OBRIGATORIO);
        return tituloRepository
                        .findById(idTitulo)
                        .orElseThrow(
                                () ->
                                        new ValidationException(
                                                EValidacao.TITULO_NAO_ENCONTRADO,
                                                idTitulo.toString()));
    }
}
