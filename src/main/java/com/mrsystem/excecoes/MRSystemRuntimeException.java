package com.mrsystem.excecoes;

import com.mrsystem.modelo.enums.EValidacao;
import lombok.Getter;

@Getter
public class MRSystemRuntimeException extends RuntimeException{

    @Getter private String descricao;

    public MRSystemRuntimeException(String msg){
        super(msg);
        this.descricao = msg;
    }

    public MRSystemRuntimeException(EValidacao validacao){
        super(validacao.toString());
        this.descricao = validacao.toString();
    }

    public MRSystemRuntimeException(Throwable causa){
        super(causa);

        this.descricao = causa.getMessage();

        if(causa instanceof MRSystemRuntimeException){
            this.descricao = ((MRSystemRuntimeException) causa).getDescricao();
        }
    }

    public MRSystemRuntimeException(String msg, Throwable causa){
        super(msg, causa);
        this.descricao = msg;
    }
}
