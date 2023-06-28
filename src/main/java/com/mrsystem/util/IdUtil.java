package com.mrsystem.util;

import com.mrsystem.excecoes.ValidationException;
import com.mrsystem.modelo.enums.EValidacao;

import java.util.UUID;

public class IdUtil {

    public static UUID obtemUUID(String valor){
        try {
            return UUID.fromString(valor);
        } catch (IllegalArgumentException e){
            throw new ValidationException(EValidacao.UUID_INVALIDO, valor);
        }
    }
}
