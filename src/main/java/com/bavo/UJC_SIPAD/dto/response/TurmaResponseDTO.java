package com.bavo.UJC_SIPAD.dto.response;

import com.bavo.UJC_SIPAD.model.Turma;
import lombok.Data;

@Data
public class TurmaResponseDTO {
    private Long idTurma;
    private String turno;
    private String ano;
    private String semestre;
    private CursoResponseDTO curso;

    public static TurmaResponseDTO fromEntity(Turma turma) {
        TurmaResponseDTO dto = new TurmaResponseDTO();
        dto.setIdTurma(turma.getIdTurma());
        dto.setTurno(turma.getTurno());
        dto.setAno(turma.getAno());
        dto.setSemestre(turma.getSemestre());
        if (turma.getCurso() != null) {
            dto.setCurso(CursoResponseDTO.fromEntity(turma.getCurso()));
        }
        return dto;
    }
}