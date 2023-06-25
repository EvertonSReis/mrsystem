package com.mrsystem.dtos.response.titulo;

import com.mrsystem.dtos.response.PaginacaoDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListagemTituloRetornoDTO {

    private PaginacaoDTO paginacao;

    private List<TituloRetornoDTO> titulos = new ArrayList<>();
}
