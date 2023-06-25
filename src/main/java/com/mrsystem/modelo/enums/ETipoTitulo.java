package com.mrsystem.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETipoTitulo {
    PAGAR("P"),
    RECEBER("R");

    private String tipo;
}
