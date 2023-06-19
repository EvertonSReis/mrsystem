package com.mrsystem.excecoes.handler;

import com.mrsystem.dtos.erro.ErroDTO;
import com.mrsystem.dtos.erro.ErrosDTO;
import com.mrsystem.excecoes.MRSystemRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(MRSystemRuntimeException.class)
    public ResponseEntity<Object> validacaoHandle(MRSystemRuntimeException ex) {
        List<ErroDTO> erros = new ArrayList<>();

        erros.add(ErroDTO.builder().codigo(-999).mensagem(ex.getDescricao()).build());
        log.error("Ocorreu um erro interno", ex);

        return new ResponseEntity<>(
                ErrosDTO.builder().erros(erros).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
