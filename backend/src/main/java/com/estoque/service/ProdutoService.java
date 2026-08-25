package com.estoque.service;

import com.estoque.dto.ProdutoRequestDTO;
import com.estoque.dto.ProdutoResponseDTO;
import com.estoque.entity.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final com.estoque.repository.ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setCategoria(dto.getCategoria());
        produto.setQuantidade(dto.getQuantidade());
// Geração do ID 
        produto = produtoRepository.save(produto);


        return toResponseDTO(produto);
    }

//* Helper privado – já usado nos demais métodos */
    private ProdutoResponseDTO toResponseDTO(Produto p) {
          ProdutoResponseDTO dto = new ProdutoResponseDTO();
          dto.setId(p.getId());
          dto.setNome(p.getNome());
          dto.setCategoria(p.getCategoria());
          dto.setQuantidade(p.getQuantidade());
          return dto;
    }

    public Page<ProdutoResponseDTO> listar(String nome, String categoria, Pageable pageable) {
          // Por enquanto ignoramos nome/categoria – retornamos tudo paginado.
          return produtoRepository.findAll(pageable)
                  .map(this::toResponseDTO);
      }

    public ProdutoResponseDTO buscarPorId(Long id) {
        // TODO: Implementar
        return null;
    }

    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        // TODO: Implementar
        return null;
    }

    @Transactional
    public void deletar(Long id) {
        // TODO: Implementar
    }
}
