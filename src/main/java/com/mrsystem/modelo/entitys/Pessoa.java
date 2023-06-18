package com.mrsystem.modelo.entitys;

import com.mrsystem.Util.TamanhoAtributos;
import com.mrsystem.modelo.enums.ETipoPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
@Entity
public class Pessoa {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id_pessoa")
    private UUID idPessoa;

    private String nome;

    @Column(name = "tipo_pessoa")
    private ETipoPessoa tipoPessoa;


    @Column(name = "cpf_cnpj")
    @TamanhoAtributos(min = 11, max = 15)
    private Integer cpfCnpj;

    private String endereco;

    private String bairro;

    private String cidade;

    private Integer numero;

    @TamanhoAtributos(max = 8)
    private String cep;

    @Column(name = "numero_telefone")
    private String numerotelefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
}
