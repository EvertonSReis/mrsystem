package com.mrsystem.dtos.response.pessoa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrsystem.dtos.request.pessoa.PessoaDTO;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PessoaRetornoDTO extends PessoaDTO implements Serializable {

    public PessoaRetornoDTO() {
        super();
    }

    private UUID idPessoa;
}
