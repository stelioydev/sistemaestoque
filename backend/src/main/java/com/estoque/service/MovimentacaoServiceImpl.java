package com.estoque.service;

import com.estoque.dto.MovimentacaoRequestDTO;
import com.estoque.dto.MovimentacaoResponseDTO;
import com.estoque.entity.Movimentacao;
import com.estoque.entity.Produto;
import com.estoque.entity.TipoMovimentacao;
import com.estoque.exception.BusinessException;
import com.estoque.repository.MovimentacaoRepository;
import com.estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    @Override
    @Transactional
    public MovimentacaoResponseDTO registrar(MovimentacaoRequestDTO dto) {
        // TODO: Implementar validação de negócio
        // 1. Buscar produto
        // 2. Validar regra: se SAIDA, estoque >= quantidade
        // 3. Atualizar estoque do produto
        // 4. Salvar movimentação
        // 5. Retornar DTO response

        // DUMMY: retorna objeto mock para testar endpoint
        return MovimentacaoResponseDTO.builder()
                .id(1L)
                .produtoId(dto.getProdutoId())
                .produtoNome("Produto Teste")
                .tipo(dto.getTipo())
                .quantidade(dto.getQuantidade())
                .dataHora(LocalDateTime.now())
                .estoqueAtual(10)
                .build();
    }

      @Override
      public List<MovimentacaoResponseDTO> listarTodas() {
          return movimentacaoRepository.findAll().stream()
                  .map(mov -> toResponseDTO(mov, mov.getProduto().getQuantidade()))
                  .toList();
      }
      @Override
      public MovimentacaoResponseDTO buscarPorId(Long id) {
          Movimentacao mov = movimentacaoRepository.findById(id)
                  .orElseThrow(() -> new BusinessException("Movimentação não encontrada"));
          return toResponseDTO(mov, mov.getProduto().getQuantidade());
      }

    // Método auxiliar para converter Entity -> DTO (quando implementar de verdade)
    private MovimentacaoResponseDTO toResponseDTO(Movimentacao mov, Integer estoqueAtual) {
        return MovimentacaoResponseDTO.builder()
                .id(mov.getId())
                .produtoId(mov.getProduto().getId())
                .produtoNome(mov.getProduto().getNome())
                .tipo(mov.getTipo())
                .quantidade(mov.getQuantidade())
                .dataHora(mov.getDataHora())
                .estoqueAtual(estoqueAtual)
                .build();
    }
}
