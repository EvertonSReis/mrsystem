package com.mrsystem.dtos.response.FormaPagamento;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrsystem.dtos.request.FormaPagamento.FormaPagamentoDTO;
import java.util.UUID;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RetornoFormaPagamentoDTO extends FormaPagamentoDTO {

    public RetornoFormaPagamentoDTO() {
        super();
    }

    private UUID idFormaPagamento;
}
