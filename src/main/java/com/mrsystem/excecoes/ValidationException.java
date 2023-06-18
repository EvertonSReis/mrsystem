package com.mrsystem.excecoes;

import com.mrsystem.modelo.enums.EValidacao;

public class ValidationException extends ExceptionAbstract {
    public ValidationException(EValidacao validacao) {
        super(validacao);
    }

    public ValidationException(EValidacao validacao, String... params){
        super(validacao, params);
    }
}
