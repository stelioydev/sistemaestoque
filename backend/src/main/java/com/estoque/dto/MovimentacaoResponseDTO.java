package com.estoque.dto;

import com.estoque.entity.TipoMovimentacao;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MovimentacaoResponseDTO {

    private Long id;
    private Long produtoId;
    private String produtoNome;
    private TipoMovimentacao tipo;
    private Integer quantidade;
    private LocalDateTime dataHora;
    private Integer estoqueAtual;
}