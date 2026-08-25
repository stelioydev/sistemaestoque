package com.estoque.dto;

import com.estoque.entity.TipoMovimentacao;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MovimentacaoRequestDTO {

    @NotNull(message = "O ID do produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "O tipo de movimentação é obrigatório (ENTRADA ou SAIDA)")
    private TipoMovimentacao tipo;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser maior que zero")
    private Integer quantidade;
}