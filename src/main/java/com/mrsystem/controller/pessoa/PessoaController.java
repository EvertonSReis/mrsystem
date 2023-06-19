package com.mrsystem.controller.pessoa;

import com.mrsystem.dtos.request.pessoa.cadastro.CadastroPessoaDTO;
import com.mrsystem.modelo.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody CadastroPessoaDTO pessoaDTO){
        return new ResponseEntity<>(pessoaService.salvar(pessoaDTO), HttpStatus.CREATED);
    }
}
