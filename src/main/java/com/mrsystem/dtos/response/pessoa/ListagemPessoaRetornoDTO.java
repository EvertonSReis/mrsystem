package com.mrsystem.dtos.response.pessoa;

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
public class ListagemPessoaRetornoDTO {

    private PaginacaoDTO paginacao;

    private List<PessoaRetornoDTO> pessoas = new ArrayList<>();
}
