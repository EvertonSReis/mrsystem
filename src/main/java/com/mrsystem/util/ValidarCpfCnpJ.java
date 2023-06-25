package com.mrsystem.util;

import com.mrsystem.dtos.request.pessoa.PessoaDTO;
import com.mrsystem.excecoes.ValidationException;
import com.mrsystem.modelo.enums.ETipoPessoa;
import com.mrsystem.modelo.enums.EValidacao;

public class ValidarCpfCnpJ {

    public static String cpfCnpjValido(PessoaDTO pessoaDTO) {

        if (!isValido(pessoaDTO.getCpfCnpj()) && pessoaDTO.getTipoPessoa() == ETipoPessoa.FISICA
                || pessoaDTO.getTipoPessoa() == ETipoPessoa.FISICA
                        && numeroCpfCnpj(pessoaDTO.getCpfCnpj()).length() != 11) {
            throw new ValidationException(EValidacao.CPF_INVALIDO, pessoaDTO.getCpfCnpj());
        } else if (!isValido(pessoaDTO.getCpfCnpj())
                        && pessoaDTO.getTipoPessoa() == ETipoPessoa.JURIDICA
                || pessoaDTO.getTipoPessoa() == ETipoPessoa.JURIDICA
                        && numeroCpfCnpj(pessoaDTO.getCpfCnpj()).length() != 14) {
            throw new ValidationException(EValidacao.CNPJ_INVALIDO, pessoaDTO.getCpfCnpj());
        }

        return numeroCpfCnpj(pessoaDTO.getCpfCnpj());
    }

    private static boolean isValido(String dados) {
        if (numeroCpfCnpj(dados).length() == 14 || numeroCpfCnpj(dados).length() == 11) return true;

        return false;
    }

    private static String numeroCpfCnpj(String cpfCnpj) {
        return cpfCnpj.replaceAll("[\\D]", "");
    }
}
