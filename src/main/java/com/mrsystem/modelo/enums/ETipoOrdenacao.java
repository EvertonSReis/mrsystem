package com.mrsystem.modelo.enums;

import org.springframework.data.domain.Sort;

public enum ETipoOrdenacao {

    ASC(Sort.Direction.ASC),
    DESC(Sort.Direction.DESC);

    public final Sort.Direction ordenacao;

    ETipoOrdenacao(Sort.Direction ordenacao) {this.ordenacao = ordenacao;}

    public static ETipoOrdenacao parseForm(String valor){
        for (ETipoOrdenacao eTipoOrdenacao : ETipoOrdenacao.values()){
            if(valor.equalsIgnoreCase(eTipoOrdenacao.toString()))
                return eTipoOrdenacao;
        }
        return ETipoOrdenacao.ASC;
    }
}
