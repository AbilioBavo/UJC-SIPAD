package com.bavo.UJC_SIPAD.service;

import com.bavo.UJC_SIPAD.dto.response.DisciplinaDocenteHistoricoResponseDTO;
import com.bavo.UJC_SIPAD.model.DisciplinaDocenteHistorico;
import com.bavo.UJC_SIPAD.repository.DisciplinaDocenteHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinaDocenteHistoricoService {
    @Autowired
    private DisciplinaDocenteHistoricoRepository historicoRepository;

    public List<DisciplinaDocenteHistoricoResponseDTO> buscarHistorico(Long disciplinaId, Long cursoId, Long turmaId, String ano, String semestre) {
        List<DisciplinaDocenteHistorico> historicos = historicoRepository.findHistorico(disciplinaId, cursoId, turmaId, ano, semestre);
        return historicos.stream()
                .map(DisciplinaDocenteHistoricoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
