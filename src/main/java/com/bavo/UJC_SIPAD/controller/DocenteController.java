package com.bavo.UJC_SIPAD.controller;

import com.bavo.UJC_SIPAD.dto.request.DocenteRequestDTO;
import com.bavo.UJC_SIPAD.dto.response.DocenteMinDTO;
import com.bavo.UJC_SIPAD.dto.response.DocenteResponseDTO;
import com.bavo.UJC_SIPAD.service.DocenteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    @Autowired
    public DocenteService service;

    @GetMapping("/")
    public List<DocenteMinDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping("/criar")
    public ResponseEntity<DocenteResponseDTO> criar(@RequestBody DocenteRequestDTO dto) {
        DocenteResponseDTO response = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteResponseDTO> buscarPorId(@PathVariable Long id) {
        DocenteResponseDTO dto = service.buscarPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteResponseDTO> atualizar(@PathVariable Long id, @RequestBody DocenteRequestDTO dto) {
        DocenteResponseDTO atualizado = service.atualizar(id, dto);
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
