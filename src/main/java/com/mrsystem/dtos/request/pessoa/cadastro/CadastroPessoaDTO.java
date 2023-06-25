package com.mrsystem.dtos.request.pessoa.cadastro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrsystem.dtos.request.pessoa.PessoaDTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CadastroPessoaDTO extends PessoaDTO {

    public CadastroPessoaDTO() {
        super();
    }

    UUID idPessoa;
}
