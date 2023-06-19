package com.mrsystem.excecoes.handler;

import com.mrsystem.dtos.erro.ErroDTO;
import com.mrsystem.dtos.erro.ErrosDTO;
import com.mrsystem.modelo.enums.EValidacao;
import com.mrsystem.util.MensagensUtils;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidationParameterHandler {

    @Autowired
    MensagensUtils mensagensUtils;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ConstraintViolationException.class})
    public ErrosDTO classArgumentNotValidException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Set<ErroDTO> erros = new HashSet<>(constraintViolations.size());

        erros.addAll(constraintViolations.stream().map(this::criarChaveMensagem).toList());

        return ErrosDTO.builder().erros(new ArrayList<>(erros)).build();
    }

    private ErroDTO criarChaveMensagem(ConstraintViolation constraintViolation) {
        Integer codigo = EValidacao.ENTRADA_DE_DADOS_INVALIDA.getCodigo();
        String mensagem;
        String error =
                constraintViolation
                        .getConstraintDescriptor()
                        .getAnnotation()
                        .annotationType()
                        .getSimpleName();
        String nomeParametro = constraintViolation.getPropertyPath().toString();

        if (nomeParametro.contains("[")) {
            mensagem =
                    MensagensUtils.getMensagem(
                            error + nomeParametro.substring(nomeParametro.lastIndexOf(".")));
            return ErroDTO.builder().codigo(codigo).mensagem(mensagem).build();
        }

        mensagem =
                MensagensUtils.getMensagem(
                        error + nomeParametro.substring(nomeParametro.indexOf(".")));

        log.warn(mensagem);
        return ErroDTO.builder().codigo(codigo).mensagem(mensagem).build();
    }
}
