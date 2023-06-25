package com.mrsystem.dtos.request.titulo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrsystem.dtos.response.FormaPagamento.RetornoFormaPagamentoDTO;
import com.mrsystem.dtos.response.pessoa.PessoaRetornoDTO;
import com.mrsystem.modelo.enums.ESituacaoTitulo;
import com.mrsystem.modelo.enums.ETipoTitulo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class TituloDTO {

    private Integer numeroTitulo;

    private PessoaRetornoDTO pessoa;

    @NotBlank private String descricao;

    private BigDecimal valorTitulo;

    private LocalDate dataEmissão;

    private LocalDate dataVencimento;

    private BigDecimal valorJuros = BigDecimal.ZERO;

    private BigDecimal valorMulta = BigDecimal.ZERO;

    private BigDecimal valorAtualizado;

    private LocalDate dataPagamento;

    private RetornoFormaPagamentoDTO formaPagamento;

    @NotNull private ETipoTitulo tipoTitulo;

    private ESituacaoTitulo situacaoTitulo = ESituacaoTitulo.ABERTO;
}
