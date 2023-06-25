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
    DATA_INVALIDA(-7),
    TIPO_PESSOA_INVALIDO(-8),
    TIPO_ORDENACAO(-9),
    ORDENAR_POR(-10),
    PESSOA_NAO_ENCONTRADA(-11),
    FORMA_PAGAMENTO_NAO_ENCONTRADA(-12),

    NAO_IDENTIFICADO(-999);

    private Integer codigo;

    EValidacao(Integer codigo) {
        this.codigo = codigo;
    }
}
