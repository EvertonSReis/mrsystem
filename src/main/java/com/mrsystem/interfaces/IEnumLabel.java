package com.mrsystem.interfaces;

import com.mrsystem.util.MensagensUtils;

public interface IEnumLabel<E extends Enum<E>> {

    default String getDescricao() {
        return MensagensUtils.getEnumLabel(this);
    }

    default String getDescricao(String[] mensagem) {
        return MensagensUtils.getEnumLabel(this, mensagem);
    }
}
