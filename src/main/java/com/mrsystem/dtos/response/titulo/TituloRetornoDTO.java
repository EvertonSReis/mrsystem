package com.mrsystem.dtos.response.titulo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrsystem.dtos.request.titulo.TituloDTO;
import java.util.UUID;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TituloRetornoDTO extends TituloDTO {

    public TituloRetornoDTO() {
        super();
    }

    private UUID idTitulo;
}
