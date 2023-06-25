package com.mrsystem.dtos.request.pessoa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrsystem.modelo.enums.ETipoPessoa;
import com.mrsystem.modelo.enums.ETipoPessoaSistema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class PessoaDTO implements Serializable {

    private Integer codigo = 0;

    @NotBlank private String nome;

    @NotNull private ETipoPessoa tipoPessoa;

    @NotBlank private String cpfCnpj;

    @NotBlank private String endereco;

    @NotBlank private String bairro;

    @NotBlank private String cidade;

    private String estado;

    private Integer numero;

    private String complemento;

    private Integer cep;

    private String numeroTelefone;

    private LocalDate dataNascimento;

    private ETipoPessoaSistema tipoPessoaSistema = ETipoPessoaSistema.CLIENTE;
}
