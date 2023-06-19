package com.mrsystem.modelo.services;

import com.mrsystem.dtos.request.pessoa.cadastro.CadastroPessoaDTO;
import com.mrsystem.dtos.response.pessoa.PessoaRetornoDTO;
import com.mrsystem.excecoes.MRSystemRuntimeException;
import com.mrsystem.excecoes.ValidationException;
import com.mrsystem.modelo.builders.PessoaBuilder;
import com.mrsystem.modelo.entitys.Pessoa;
import com.mrsystem.modelo.enums.ETipoPessoa;
import com.mrsystem.modelo.enums.EValidacao;
import com.mrsystem.modelo.repository.PessoaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Slf4j
@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaBuilder pessoaBuilder;

  public PessoaRetornoDTO salvar(CadastroPessoaDTO pessoaDTO){
        try {
            verificarExisteCpfCnpjCadastrado(pessoaDTO);
            pessoaDTO.setCodigo(codigoCliente());
            Pessoa pessoa = pessoaRepository.save(pessoaBuilder.parserCadastroPessoa(pessoaDTO));
            return pessoaBuilder.builderRetornoPessoa(pessoa);
        } catch (ValidationException ex){
            throw ex;
        } catch (Exception e){
            log.error(format("Ocorreu um erro ao salvar o cliente no banco de dados."),e);
            throw new MRSystemRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }

    private boolean verificarExisteCpfCnpjCadastrado(CadastroPessoaDTO pessoaDTO) {
        if (pessoaDTO.getTipoPessoa() == ETipoPessoa.FISICA) {
            if (pessoaRepository.existeCadastroParaCpfCnpj(pessoaDTO.getCpfCnpj())) {
                throw new ValidationException(EValidacao.CPF_JA_CADASTRADO, pessoaDTO.getCpfCnpj().toString());
            }
        } else {
            if (pessoaRepository.existeCadastroParaCpfCnpj(pessoaDTO.getCpfCnpj())) {
                throw new ValidationException(EValidacao.CNPJ_JA_CADASTRADO, pessoaDTO.getCpfCnpj().toString());
            }
        }
        return true;
    }

    private Integer codigoCliente(){
      if(pessoaRepository.ultimoCodigoCliente() == null){
          return 1;
      } else {
          return pessoaRepository.ultimoCodigoCliente() + 1;
      }
    }
}
