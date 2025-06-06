package com.bavo.UJC_SIPAD.controller;

import com.bavo.UJC_SIPAD.dto.request.TurmaRequestDTO;
import com.bavo.UJC_SIPAD.dto.response.TurmaResponseDTO;
import com.bavo.UJC_SIPAD.service.TurmaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @GetMapping("/")
    public List<TurmaResponseDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping("/criar")
    public ResponseEntity<TurmaResponseDTO> criar(@RequestBody TurmaRequestDTO dto) {
        TurmaResponseDTO response = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> buscarPorId(@PathVariable Long id) {
        TurmaResponseDTO dto = service.buscarPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> atualizar(@PathVariable Long id, @RequestBody TurmaRequestDTO dto) {
        TurmaResponseDTO atualizado = service.atualizar(id, dto);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        boolean removido = service.apagarPorId(id);
        if (removido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/disciplinas")
    public ResponseEntity<TurmaResponseDTO> associarDisciplinas(@PathVariable Long id, @RequestBody List<Long> disciplinaIds) {
        TurmaResponseDTO response = service.associarDisciplinas(id, disciplinaIds);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}