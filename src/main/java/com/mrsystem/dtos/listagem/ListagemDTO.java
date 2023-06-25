package com.mrsystem.dtos.listagem;

import com.mrsystem.modelo.enums.EOrdenacao;
import com.mrsystem.modelo.enums.ETipoOrdenacao;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListagemDTO implements Serializable {

    private Integer numeroPagina;
    private Integer itensPorPagina;
    private ETipoOrdenacao tipoOrdenacao;
    private EOrdenacao ordenarPor;
}
