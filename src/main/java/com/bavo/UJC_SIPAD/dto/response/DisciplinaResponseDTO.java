package com.bavo.UJC_SIPAD.dto.response;

import com.bavo.UJC_SIPAD.model.Disciplina;
import lombok.Data;

@Data
public class DisciplinaResponseDTO {
    private Long idDisciplina;
    private String nome;
    private String cargaHoraria;

    public static DisciplinaResponseDTO fromEntity(Disciplina disciplina) {
        DisciplinaResponseDTO dto = new DisciplinaResponseDTO();
        dto.setIdDisciplina(disciplina.getIdDisciplina());
        dto.setNome(disciplina.getNome());
        dto.setCargaHoraria(disciplina.getCargaHoraria());
        return dto;
    }
}