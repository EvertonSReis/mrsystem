package com.mrsystem.modelo.entitys;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tipoPagamento")
@Entity
public class FormaPagamento {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id_forma_pagamento")
    private UUID idFormaPagamento;

    private String nome;

    @OneToMany(mappedBy = "formaPagamento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Titulo> titulo;
}
