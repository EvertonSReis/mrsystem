package com.mrsystem.modelo.builders;

import com.mrsystem.dtos.request.FormaPagamento.cadastro.CadastroFormaPagamentoDTO;
import com.mrsystem.dtos.response.FormaPagamento.RetornoFormaPagamentoDTO;
import com.mrsystem.modelo.entitys.FormaPagamento;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoBuilder {

    public FormaPagamento parserCadastroFormaPagamento(CadastroFormaPagamentoDTO formaPagamento) {
        FormaPagamento pagamento = new FormaPagamento();

        pagamento.setNome(formaPagamento.getNome());

        return pagamento;
    }

    public RetornoFormaPagamentoDTO builderRetornoFormaPagamento(FormaPagamento formaPagamento) {
        RetornoFormaPagamentoDTO retornoDTO = new RetornoFormaPagamentoDTO();

        retornoDTO.setIdFormaPagamento(formaPagamento.getIdFormaPagamento());
        retornoDTO.setNome(formaPagamento.getNome());

        return retornoDTO;
    }
}
