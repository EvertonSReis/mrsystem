package com.mrsystem.modelo.builders;

import com.mrsystem.dtos.request.pessoa.cadastro.CadastroPessoaDTO;
import com.mrsystem.dtos.response.pessoa.PessoaRetornoDTO;
import com.mrsystem.modelo.entitys.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaBuilder {

    public Pessoa parserCadastroPessoa(CadastroPessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();

        pessoa.setCodigo(pessoa.getCodigo());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setTipoPessoa(pessoaDTO.getTipoPessoa());
        pessoa.setCpfCnpj(pessoaDTO.getCpfCnpj());
        pessoa.setEndereco(pessoaDTO.getEndereco());
        pessoa.setBairro(pessoaDTO.getBairro());
        pessoa.setCidade(pessoaDTO.getCidade());
        pessoa.setNumero(pessoaDTO.getNumero());
        pessoa.setCep(pessoaDTO.getCep());
        pessoa.setNumeroTelefone(pessoaDTO.getNumeroTelefone());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());

        return pessoa;
    }

    public PessoaRetornoDTO builderRetornoPessoa(Pessoa pessoa){
        PessoaRetornoDTO retornoDTO = new PessoaRetornoDTO();

        retornoDTO.setIdPessoa(pessoa.getIdPessoa());
        retornoDTO.setCodigo(pessoa.getCodigo());
        retornoDTO.setNome(pessoa.getNome());
        retornoDTO.setTipoPessoa(pessoa.getTipoPessoa());
        retornoDTO.setCpfCnpj(pessoa.getCpfCnpj());
        retornoDTO.setEndereco(pessoa.getEndereco());
        retornoDTO.setBairro(pessoa.getBairro());
        retornoDTO.setCidade(pessoa.getCidade());
        retornoDTO.setNumero(pessoa.getNumero());
        retornoDTO.setCep(pessoa.getCep());
        retornoDTO.setNumeroTelefone(pessoa.getNumeroTelefone());
        retornoDTO.setDataNascimento(pessoa.getDataNascimento());

        return retornoDTO;
    }
}
