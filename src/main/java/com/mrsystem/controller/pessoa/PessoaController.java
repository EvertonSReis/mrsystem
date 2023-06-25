package com.mrsystem.controller.pessoa;

import com.mrsystem.dtos.listagem.ListagemDTO;
import com.mrsystem.dtos.request.pessoa.cadastro.CadastroPessoaDTO;
import com.mrsystem.dtos.response.pessoa.ListagemPessoaRetornoDTO;
import com.mrsystem.modelo.enums.EOrdenacao;
import com.mrsystem.modelo.enums.ETipoOrdenacao;
import com.mrsystem.modelo.enums.ETipoPessoa;
import com.mrsystem.modelo.enums.EValidacao;
import com.mrsystem.modelo.services.PessoaService;
import com.mrsystem.util.EnumValido;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody CadastroPessoaDTO pessoaDTO) {
        return new ResponseEntity<>(pessoaService.salvar(pessoaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> retornarTodasPessoas(
            @RequestParam(name = "numeroPagina", defaultValue = "1") Integer numeroPagina,
            @RequestParam(name = "itensPorPagina", defaultValue = "10") Integer itensPorPagina,
            @EnumValido(enumClass = ETipoPessoa.class, validacao = EValidacao.TIPO_ORDENACAO)
                    @RequestParam(name = "tipoOrdenacao", defaultValue = "DESC")
                    String tipoOrdenacao,
            @EnumValido(enumClass = ETipoPessoa.class, validacao = EValidacao.ORDENAR_POR)
                    @RequestParam(name = "ordenarPor", required = false)
                    String ordenarPor) {

        ListagemPessoaRetornoDTO pessoaRetornoDTOS =
                pessoaService.retornarTodosClientes(
                        new ListagemDTO(
                                numeroPagina,
                                itensPorPagina,
                                ETipoOrdenacao.parseForm(tipoOrdenacao.toUpperCase()),
                                ordenarPor != null
                                        ? EOrdenacao.valueOf(ordenarPor)
                                        : EOrdenacao.NOME));

        return new ResponseEntity<>(pessoaRetornoDTOS, HttpStatus.OK);
    }
}
