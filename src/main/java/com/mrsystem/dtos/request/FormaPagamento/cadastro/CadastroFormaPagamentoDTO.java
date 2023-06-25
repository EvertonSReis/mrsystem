package com.mrsystem.dtos.request.FormaPagamento.cadastro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrsystem.dtos.request.FormaPagamento.FormaPagamentoDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CadastroFormaPagamentoDTO extends FormaPagamentoDTO {

    public CadastroFormaPagamentoDTO() {
        super();
    }
}
