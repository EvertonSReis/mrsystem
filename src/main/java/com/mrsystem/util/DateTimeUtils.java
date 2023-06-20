package com.mrsystem.util;

import com.mrsystem.excecoes.ValidationException;
import com.mrsystem.modelo.enums.EValidacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtils {

    public static LocalDateTime paraLocalDateTimeIso(String data) {
        if (data == null) return null;

        try {
            return LocalDateTime.parse(data, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException ex) {
            throw new ValidationException(EValidacao.DATA_INVALIDA);
        }
    }
}
