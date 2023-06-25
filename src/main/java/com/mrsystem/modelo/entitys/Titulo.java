package com.mrsystem.modelo.entitys;

import com.mrsystem.modelo.enums.ESituacaoTitulo;
import com.mrsystem.modelo.enums.ETipoTitulo;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "titulo")
@Entity
public class Titulo {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id_titulo")
    private UUID idTitulo;

    @Column(name = "numero_titulo")
    private Integer numeroTitulo = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    private String descricao;

    @Column(name = "valor_titulo")
    private BigDecimal valorTitulo;

    @Column(name = "data_emissao")
    private LocalDate dataEmissão = LocalDate.now();

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "valor_juros")
    private BigDecimal valorJuros;

    @Column(name = "valor_multa")
    private BigDecimal valorMulta;

    @Column(name = "valor_titulo_atualizado")
    private BigDecimal valorTituloAtualizado;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_forma_pagamento")
    private FormaPagamento formaPagamento;

    @Column(name = "tipo_titulo")
    private ETipoTitulo tipoTitulo;

    private ESituacaoTitulo situacaoTitulo = ESituacaoTitulo.ABERTO;
}
