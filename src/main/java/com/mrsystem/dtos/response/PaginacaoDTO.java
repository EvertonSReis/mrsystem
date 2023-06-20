package com.mrsystem.dtos.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaginacaoDTO {

    private Integer pagina;
    private Integer itensPorPagina;
    private Integer quantidadeDePaginas;
    private long quantidadeTotalDeItens;
}
