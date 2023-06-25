package com.mrsystem.controller.titulo;

import com.mrsystem.dtos.request.titulo.cadastro.CadastroTituloDTO;
import com.mrsystem.modelo.services.TituloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/titulo", produces = "application/json")
public class TituloController {

    @Autowired TituloService tituloService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CadastroTituloDTO tituloDTO) {
        return new ResponseEntity<>(tituloService.cadastrar(tituloDTO), HttpStatus.CREATED);
    }
}
