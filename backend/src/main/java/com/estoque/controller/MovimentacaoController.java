package com.estoque.controller;

import com.estoque.dto.MovimentacaoRequestDTO;
import com.estoque.dto.MovimentacaoResponseDTO;
import com.estoque.service.MovimentacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService service;

    @PostMapping
    public ResponseEntity<MovimentacaoResponseDTO> registrar(@Valid @RequestBody MovimentacaoRequestDTO dto) {
        MovimentacaoResponseDTO response = service.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoResponseDTO>> listarTodas() {
        List<MovimentacaoResponseDTO> lista = service.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        MovimentacaoResponseDTO response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
}