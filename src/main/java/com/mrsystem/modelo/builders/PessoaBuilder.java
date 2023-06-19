package com.mrsystem.modelo.builders;

import com.mrsystem.dtos.request.pessoa.cadastro.CadastroPessoaDTO;
import com.mrsystem.dtos.response.pessoa.PessoaRetornoDTO;
import com.mrsystem.modelo.entitys.Pessoa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PessoaBuilder {

    public Pessoa parserCadastroPessoa(CadastroPessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();

        pessoa.setCodigo(pessoaDTO.getCodigo());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setTipoPessoa(pessoaDTO.getTipoPessoa());
        pessoa.setCpfCnpj(pessoaDTO.getCpfCnpj());
        pessoa.setEndereco(pessoaDTO.getEndereco());
        pessoa.setBairro(pessoaDTO.getBairro());
        pessoa.setCidade(pessoaDTO.getCidade());
        pessoa.setNumero(pessoaDTO.getNumero());
        pessoa.setComplemtento(pessoaDTO.getComplemento());
        pessoa.setCep(pessoaDTO.getCep());
        pessoa.setNumeroTelefone(pessoaDTO.getNumeroTelefone());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoa.setTipoPessoaSistema(pessoaDTO.getTipoPessoaSistema());

        return pessoa;
    }

    public List<PessoaRetornoDTO> builderRetornoListaPessoa(List<Pessoa> pessoas){
        List<PessoaRetornoDTO> pessoaRetornoDTOS = new ArrayList<>();

        pessoas.forEach(pessoa -> pessoaRetornoDTOS.add(builderRetornoPessoa(pessoa)));

        return pessoaRetornoDTOS;
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
        retornoDTO.setComplemento(pessoa.getComplemtento());
        retornoDTO.setCep(pessoa.getCep());
        retornoDTO.setNumeroTelefone(pessoa.getNumeroTelefone());
        retornoDTO.setDataNascimento(pessoa.getDataNascimento());
        retornoDTO.setTipoPessoaSistema(pessoa.getTipoPessoaSistema());

        return retornoDTO;
    }
}
