package com.bavo.UJC_SIPAD.controller;

import com.bavo.UJC_SIPAD.dto.response.DisciplinaDocenteHistoricoResponseDTO;
import com.bavo.UJC_SIPAD.service.DisciplinaDocenteHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/historico-docentes")
public class DisciplinaDocenteHistoricoController {
    @Autowired
    private DisciplinaDocenteHistoricoService service;

    @GetMapping("/")
    public List<DisciplinaDocenteHistoricoResponseDTO> buscarHistorico(
            @RequestParam(required = false) Long disciplinaId,
            @RequestParam(required = false) Long cursoId,
            @RequestParam(required = false) Long turmaId,
            @RequestParam(required = false) String ano,
            @RequestParam(required = false) String semestre
    ) {
        return service.buscarHistorico(disciplinaId, cursoId, turmaId, ano, semestre);
    }
}
