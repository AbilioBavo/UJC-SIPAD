package com.bavo.UJC_SIPAD.controller;

import com.bavo.UJC_SIPAD.dto.request.DisciplinaRequestDTO;
import com.bavo.UJC_SIPAD.dto.response.DisciplinaResponseDTO;
import com.bavo.UJC_SIPAD.service.DisciplinaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService service;

    @GetMapping("/")
    public List<DisciplinaResponseDTO> listar() {
        return service.listarTodosDTO();
    }

    @PostMapping("/criar")
    public ResponseEntity<DisciplinaResponseDTO> criar(@RequestBody DisciplinaRequestDTO dto) {
        DisciplinaResponseDTO response = service.salvarDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(@PathVariable Long id) {
        DisciplinaResponseDTO dto = service.buscarPorIdDTO(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> atualizar(@PathVariable Long id, @RequestBody DisciplinaRequestDTO dto) {
        DisciplinaResponseDTO atualizado = service.atualizarDTO(id, dto);
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
}