package com.mrsystem.modelo.enums;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EOrdenacao {
    NOME("nome"),
    CPF_CNPJ("cpfCnpj");

    private String nome;
}
