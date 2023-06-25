package com.mrsystem.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ESituacaoTitulo {
    ABERTO("A"),
    PAGO("P");

    private String tipo;
}
