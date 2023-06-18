package com.mrsystem.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETipoPessoa {

    FISICA(0),
    JURIDICA(1);

    private Integer codigo;

}
