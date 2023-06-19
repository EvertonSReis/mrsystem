package com.mrsystem.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETipoPessoaSistema {

    FUNCIONARIO("FUNC"),
    CLIENTE("CLI"),
    FORNECEDOR("FORN");

    private String name;

}
