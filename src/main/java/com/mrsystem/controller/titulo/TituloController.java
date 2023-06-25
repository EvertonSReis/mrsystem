package com.mrsystem.controller.titulo;

import com.mrsystem.dtos.listagem.ListagemDTO;
import com.mrsystem.dtos.request.titulo.cadastro.CadastroTituloDTO;
import com.mrsystem.dtos.response.titulo.ListagemTituloRetornoDTO;
import com.mrsystem.modelo.enums.EOrdenacao;
import com.mrsystem.modelo.enums.ETipoOrdenacao;
import com.mrsystem.modelo.enums.ETipoPessoa;
import com.mrsystem.modelo.enums.EValidacao;
import com.mrsystem.modelo.services.TituloService;
import com.mrsystem.util.EnumValido;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/titulo", produces = "application/json")
public class TituloController {

    @Autowired TituloService tituloService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CadastroTituloDTO tituloDTO) {
        return new ResponseEntity<>(tituloService.cadastrar(tituloDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> retornarTodosTitulos(
            @RequestParam(name = "numeroPagina", defaultValue = "1") Integer numeroPagina,
            @RequestParam(name = "itensPorPagina", defaultValue = "10") Integer itensPorPagina,
            @EnumValido(enumClass = ETipoPessoa.class, validacao = EValidacao.TIPO_ORDENACAO)
                    @RequestParam(name = "tipoOrdenacao", defaultValue = "DESC")
                    String tipoOrdenacao,
            @EnumValido(enumClass = ETipoPessoa.class, validacao = EValidacao.ORDENAR_POR)
                    @RequestParam(name = "ordenarPor", required = false)
                    String ordenarPor) {

        ListagemTituloRetornoDTO listagemTituloRetornoDTO =
                tituloService.retornarTodosTitulos(
                        new ListagemDTO(
                                numeroPagina,
                                itensPorPagina,
                                ETipoOrdenacao.parseForm(tipoOrdenacao.toUpperCase()),
                                ordenarPor != null
                                        ? EOrdenacao.valueOf(ordenarPor)
                                        : EOrdenacao.NUMERO_TITULO));
        return new ResponseEntity<>(listagemTituloRetornoDTO, HttpStatus.OK);
    }
}
