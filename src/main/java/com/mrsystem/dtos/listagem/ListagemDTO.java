package com.mrsystem.dtos.listagem;

import com.mrsystem.modelo.enums.EOrdenacao;
import com.mrsystem.modelo.enums.ETipoOrdenacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
