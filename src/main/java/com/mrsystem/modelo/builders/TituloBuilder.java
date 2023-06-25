package com.mrsystem.modelo.builders;

import com.mrsystem.dtos.request.titulo.cadastro.CadastroTituloDTO;
import com.mrsystem.dtos.response.titulo.TituloRetornoDTO;
import com.mrsystem.modelo.entitys.FormaPagamento;
import com.mrsystem.modelo.entitys.Pessoa;
import com.mrsystem.modelo.entitys.Titulo;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TituloBuilder {

    @Autowired PessoaBuilder pessoaBuilder;
    @Autowired FormaPagamentoBuilder formaPagamentoBuilder;

    public Titulo parserCadastroTitulo(
            CadastroTituloDTO tituloDTO,
            Pessoa pessoa,
            FormaPagamento formaPagamento,
            Integer numeroTitulo,
            BigDecimal valorAtualizado) {
        Titulo titulo = new Titulo();

        titulo.setNumeroTitulo(numeroTitulo);
        titulo.setPessoa(pessoa);
        titulo.setDescricao(tituloDTO.getDescricao());
        titulo.setValorTitulo(tituloDTO.getValorTitulo());
        titulo.setDataEmissão(tituloDTO.getDataEmissão());
        titulo.setValorJuros(tituloDTO.getValorJuros());
        titulo.setValorMulta(tituloDTO.getValorMulta());
        titulo.setValorTituloAtualizado(valorAtualizado);
        titulo.setDataPagamento(tituloDTO.getDataPagamento());
        titulo.setDataPagamento(tituloDTO.getDataPagamento());
        titulo.setFormaPagamento(formaPagamento);
        titulo.setSituacaoTitulo(tituloDTO.getSituacaoTitulo());

        return titulo;
    }

    public TituloRetornoDTO builderRetornoTitulo(Titulo titulo) {
        TituloRetornoDTO retornoDTO = new TituloRetornoDTO();

        retornoDTO.setIdTitulo(titulo.getIdTitulo());
        retornoDTO.setNumeroTitulo(titulo.getNumeroTitulo());
        retornoDTO.setPessoa(pessoaBuilder.builderRetornoPessoa(titulo.getPessoa()));
        retornoDTO.setDescricao(titulo.getDescricao());
        retornoDTO.setValorTitulo(titulo.getValorTitulo());
        retornoDTO.setDataEmissão(titulo.getDataEmissão());
        retornoDTO.setValorJuros(titulo.getValorJuros());
        retornoDTO.setValorMulta(titulo.getValorMulta());
        retornoDTO.setDataPagamento(titulo.getDataPagamento());
        retornoDTO.setFormaPagamento(
                formaPagamentoBuilder.builderRetornoFormaPagamento(titulo.getFormaPagamento()));
        retornoDTO.setTipoTitulo(titulo.getTipoTitulo());
        retornoDTO.setSituacaoTitulo(titulo.getSituacaoTitulo());

        return retornoDTO;
    }
}
