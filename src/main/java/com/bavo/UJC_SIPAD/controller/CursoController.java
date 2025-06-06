package com.bavo.UJC_SIPAD.controller;

import com.bavo.UJC_SIPAD.dto.request.CursoRequestDTO;
import com.bavo.UJC_SIPAD.dto.response.CursoResponseDTO;
import com.bavo.UJC_SIPAD.dto.response.DisciplinaResponseDTO;
import com.bavo.UJC_SIPAD.service.CursoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    public CursoService service;

    @GetMapping("/")
    public List<CursoResponseDTO> listar() {
        return service.listarTodosDTO();
    }

    @PostMapping("/criar")
    public ResponseEntity<CursoResponseDTO> criar(@RequestBody CursoRequestDTO dto) {
        CursoResponseDTO response = service.salvarDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> buscarPorId(@PathVariable Long id) {
        CursoResponseDTO dto = service.buscarPorIdDTO(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> atualizar(@PathVariable Long id, @RequestBody CursoRequestDTO dto) {
        CursoResponseDTO atualizado = service.atualizarDTO(id, dto);
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
    public ResponseEntity<CursoResponseDTO> associarDisciplinas(@PathVariable Long id, @RequestBody List<Long> disciplinaIds) {
        CursoResponseDTO response = service.associarDisciplinas(id, disciplinaIds);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/disciplinas")
    public ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinasDoCurso(@PathVariable Long id) {
        CursoResponseDTO curso = service.buscarPorIdDTO(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        List<DisciplinaResponseDTO> disciplinas = service.listarDisciplinasDoCurso(id);
        return ResponseEntity.ok(disciplinas);
    }
}
