package com.mrsystem.modelo.enums;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EOrdenacao {
    NOME("nome"),
    CPF_CNPJ("cpfCnpj"),
    NUMERO_TITULO("numeroTitulo"),
    TIPO_TITULO("tipoTitulo"),
    SITUACAO_TITULO("situacaoTitulo");

    private String nome;
}
