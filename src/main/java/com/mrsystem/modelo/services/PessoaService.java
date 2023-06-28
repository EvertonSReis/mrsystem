package com.mrsystem.modelo.services;

import static java.lang.String.format;

import com.mrsystem.dtos.listagem.ListagemDTO;
import com.mrsystem.dtos.request.pessoa.cadastro.CadastroPessoaDTO;
import com.mrsystem.dtos.response.PaginacaoDTO;
import com.mrsystem.dtos.response.pessoa.ListagemPessoaRetornoDTO;
import com.mrsystem.dtos.response.pessoa.PessoaRetornoDTO;
import com.mrsystem.excecoes.MRSystemRuntimeException;
import com.mrsystem.excecoes.ValidationException;
import com.mrsystem.modelo.builders.PessoaBuilder;
import com.mrsystem.modelo.entitys.Pessoa;
import com.mrsystem.modelo.enums.ETipoPessoa;
import com.mrsystem.modelo.enums.EValidacao;
import com.mrsystem.modelo.repository.PessoaRepository;
import com.mrsystem.util.IdUtil;
import com.mrsystem.util.ValidarCpfCnpJ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PessoaService {

    @Autowired PessoaRepository pessoaRepository;

    @Autowired PessoaBuilder pessoaBuilder;

    public PessoaRetornoDTO salvar(CadastroPessoaDTO pessoaDTO) {
        try {
            verificarExisteCpfCnpjCadastrado(pessoaDTO);
            pessoaDTO.setCodigo(codigoCliente());
            String cpfCnpj = ValidarCpfCnpJ.cpfCnpjValido(pessoaDTO);
            pessoaDTO.setCpfCnpj(cpfCnpj);
            Pessoa pessoa = pessoaRepository.save(pessoaBuilder.parserCadastroPessoa(pessoaDTO));
            return pessoaBuilder.builderRetornoPessoa(pessoa);
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception e) {
            log.error(format("Ocorreu um erro ao salvar o cliente no banco de dados."), e);
            throw new MRSystemRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }

    private boolean verificarExisteCpfCnpjCadastrado(CadastroPessoaDTO pessoaDTO) {
        if (pessoaDTO.getTipoPessoa() == ETipoPessoa.FISICA) {
            if (pessoaRepository.existeCadastroParaCpfCnpj(pessoaDTO.getCpfCnpj())) {
                throw new ValidationException(
                        EValidacao.CPF_JA_CADASTRADO, pessoaDTO.getCpfCnpj().toString());
            }
        } else {
            if (pessoaRepository.existeCadastroParaCpfCnpj(pessoaDTO.getCpfCnpj())) {
                throw new ValidationException(
                        EValidacao.CNPJ_JA_CADASTRADO, pessoaDTO.getCpfCnpj().toString());
            }
        }
        return true;
    }

    private Integer codigoCliente() {
        if (pessoaRepository.ultimoCodigoCliente() == null) {
            return 1;
        } else {
            return pessoaRepository.ultimoCodigoCliente() + 1;
        }
    }

    public ListagemPessoaRetornoDTO retornarTodosClientes(ListagemDTO listagemDTO) {

        try {
            Page<Pessoa> pessoas = obterPaginacaoPessoa(listagemDTO);

            ListagemPessoaRetornoDTO retorno = new ListagemPessoaRetornoDTO();
            if (pessoas.getContent().size() > 0) {
                retorno.setPaginacao(
                        new PaginacaoDTO(
                                listagemDTO.getNumeroPagina(),
                                listagemDTO.getItensPorPagina(),
                                pessoas.getTotalPages(),
                                pessoas.getTotalElements()));

                pessoas.getContent()
                        .forEach(
                                pessoa ->
                                        retorno.getPessoas()
                                                .add(pessoaBuilder.builderRetornoPessoa(pessoa)));
            }
            return retorno;
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception e) {
            log.error(format("Ocorreu um erro ao retornar todos clientes"), e);
            throw new MRSystemRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }

    private Page<Pessoa> obterPaginacaoPessoa(ListagemDTO listagemDTO) {
        Pageable pageable =
                PageRequest.of(
                        listagemDTO.getNumeroPagina() > 0 ? (listagemDTO.getNumeroPagina() - 1) : 0,
                        listagemDTO.getItensPorPagina(),
                        Sort.by(
                                listagemDTO.getTipoOrdenacao().ordenacao,
                                listagemDTO.getOrdenarPor().getNome()));

        return pessoaRepository.findAll(pageable);
    }

    @Transactional
    public void excluir(String idPessoa) {
        /*try {
            UUID uuidIdPessoa = IdUtil.obtemUUID(idPessoa);
            verificarExisteVinculoComTitulo(uuidIdPessoa);
        }*/
    }

    private void verificarExisteVinculoComTitulo(UUID uuidIdPessoa) {}
        //ponto parada


}
