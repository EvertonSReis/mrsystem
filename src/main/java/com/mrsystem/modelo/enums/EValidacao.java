package com.mrsystem.modelo.enums;

import com.mrsystem.interfaces.IEnumLabel;
import lombok.Getter;

@Getter
public enum EValidacao implements IEnumLabel<EValidacao> {

    ENTRADA_DE_DADOS_INVALIDA(-1),
    CAMPO_INVALIDO_NAO_IDENTIFICADO(-2),
    CPF_JA_CADASTRADO(-3),
    CNPJ_JA_CADASTRADO(-4),
    CPF_INVALIDO(-5),
    CNPJ_INVALIDO(-6),
    NAO_IDENTIFICADO(-999);

    private Integer codigo;

    EValidacao(Integer codigo){ this.codigo = codigo;}

}
