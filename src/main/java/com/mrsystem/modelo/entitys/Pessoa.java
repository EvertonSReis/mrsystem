package com.mrsystem.modelo.entitys;

import com.mrsystem.modelo.enums.ETipoPessoa;
import com.mrsystem.modelo.enums.ETipoPessoaSistema;
import com.mrsystem.util.TamanhoAtributos;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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

    private Integer codigo;

    private String nome;

    @Column(name = "tipo_pessoa")
    private ETipoPessoa tipoPessoa;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    private String endereco;

    private String bairro;

    private String cidade;

    private String estado;

    private Integer numero;

    private String complemtento;

    @TamanhoAtributos(max = 8)
    private Integer cep;

    @Column(name = "numero_telefone")
    private String numeroTelefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated
    @Column(name = "tipo_pessoa_sistema")
    private ETipoPessoaSistema tipoPessoaSistema;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Titulo> titulo;
}
