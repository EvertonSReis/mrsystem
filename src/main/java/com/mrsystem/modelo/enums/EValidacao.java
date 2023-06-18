package com.mrsystem.modelo.enums;

import com.mrsystem.interfaces.IEnumLabel;
import lombok.Getter;

@Getter
public enum EValidacao implements IEnumLabel<EValidacao> {

    ENTRADA_DE_DADOS_INVALIDA(-1),
    CAMPO_INVALIDO_NAO_IDENTIFICADO(-2),
    NAO_IDENTIFICADO(-999);

    private Integer codigo;

    EValidacao(Integer codigo){ this.codigo = codigo;}

}
