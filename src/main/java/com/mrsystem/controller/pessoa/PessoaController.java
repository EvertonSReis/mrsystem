package com.mrsystem.controller.pessoa;

import com.mrsystem.dtos.listagem.ListagemDTO;
import com.mrsystem.dtos.request.pessoa.cadastro.CadastroPessoaDTO;
import com.mrsystem.dtos.response.pessoa.ListagemPessoaRetornoDTO;
import com.mrsystem.dtos.response.pessoa.PessoaRetornoDTO;
import com.mrsystem.modelo.enums.EOrdenacao;
import com.mrsystem.modelo.enums.ETipoOrdenacao;
import com.mrsystem.modelo.enums.EValidacao;
import com.mrsystem.modelo.services.PessoaService;
import com.mrsystem.util.DateTimeUtils;
import com.mrsystem.util.EnumValido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody CadastroPessoaDTO pessoaDTO){
        return new ResponseEntity<>(pessoaService.salvar(pessoaDTO), HttpStatus.CREATED);
    }

    /*
    * INSERIR PAGINAÇÃO.
    */
    @GetMapping
    public ResponseEntity<?> retornarTodasPessoas(
            @RequestParam(name = "numeroPagina", defaultValue = "1") Integer numeroPagina,
            @RequestParam(name = "itensPorPagina", defaultValue = "10") Integer itensPorPagina,
            @EnumValido( enumClass = ETipoOrdenacao.class,
                         message = "Tipo de ordenação informado é inválido.")
                @RequestParam(name = "tipoOrdenacao", defaultValue = "DESC") String tipoOrdenacao,
            @EnumValido( enumClass = EOrdenacao.class,
                    message = "Ordenação informada é inválida.")
            @RequestParam(name = "ordenarPor", required = false)String ordenarPor){

        ListagemPessoaRetornoDTO pessoaRetornoDTOS = pessoaService.retornarTodosClientes(new ListagemDTO(numeroPagina,
                itensPorPagina,
                ETipoOrdenacao.parseForm(tipoOrdenacao.toUpperCase()),
                ordenarPor != null ? EOrdenacao.valueOf(ordenarPor) : EOrdenacao.NOME));

        return new ResponseEntity<>(pessoaRetornoDTOS, HttpStatus.OK);
    }
}
