package com.estoque.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres")
    private String nome;

    @NotBlank(message = "A categoria é obrigatória")
    @Size(max = 100, message = "A categoria deve ter no máximo 100 caracteres")
    private String categoria;

    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Integer quantidade = 0;
}