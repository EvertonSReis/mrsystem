package com.mrsystem.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ESituacaoTitulo {
    ABERTO("A"),
    VENCIDO("V"),
    PAGO("P");

    private String tipo;
}
