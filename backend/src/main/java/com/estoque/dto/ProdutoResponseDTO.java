package com.estoque.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String categoria;
    private Integer quantidade;
}