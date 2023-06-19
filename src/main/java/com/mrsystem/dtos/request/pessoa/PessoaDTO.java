package com.mrsystem.dtos.request.pessoa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrsystem.modelo.enums.ETipoPessoa;
import com.mrsystem.modelo.enums.ETipoPessoaSistema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class PessoaDTO implements Serializable {

    private Integer codigo = 0;
    @NotNull private String nome;
    private ETipoPessoa tipoPessoa;
    @NotNull private String cpfCnpj;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer numero;
    private String complemento;
    private Integer cep;
    private String numeroTelefone;
    private LocalDate dataNascimento;
    private ETipoPessoaSistema tipoPessoaSistema = ETipoPessoaSistema.CLIENTE;
}
