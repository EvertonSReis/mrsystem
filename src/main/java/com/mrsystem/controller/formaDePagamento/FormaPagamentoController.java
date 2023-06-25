package com.mrsystem.controller.formaDePagamento;

import com.mrsystem.dtos.request.FormaPagamento.cadastro.CadastroFormaPagamentoDTO;
import com.mrsystem.modelo.services.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/forma-pagamento", produces = "application/json")
public class FormaPagamentoController {

    @Autowired FormaPagamentoService formaPagamentoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(
            @Valid @RequestBody CadastroFormaPagamentoDTO formaPagamentoDTO) {
        return new ResponseEntity<>(
                formaPagamentoService.cadastrar(formaPagamentoDTO), HttpStatus.CREATED);
    }
}
