package com.mrsystem.dtos.request.titulo.cadastro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrsystem.dtos.request.titulo.TituloDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CadastroTituloDTO extends TituloDTO {

    public CadastroTituloDTO() {
        super();
    }
}
