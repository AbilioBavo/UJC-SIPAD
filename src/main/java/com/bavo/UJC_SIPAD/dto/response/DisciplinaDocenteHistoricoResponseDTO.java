package com.bavo.UJC_SIPAD.dto.response;

import com.bavo.UJC_SIPAD.model.DisciplinaDocenteHistorico;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DisciplinaDocenteHistoricoResponseDTO {
    private Long id;
    private Long disciplinaId;
    private String disciplinaNome;
    private Long docenteId;
    private String docenteNome;
    private Long turmaId;
    private String turmaAno;
    private String turmaSemestre;
    private String turmaTurno;
    private LocalDateTime dataAlocacao;

    public static DisciplinaDocenteHistoricoResponseDTO fromEntity(DisciplinaDocenteHistorico h) {
        DisciplinaDocenteHistoricoResponseDTO dto = new DisciplinaDocenteHistoricoResponseDTO();
        dto.setId(h.getId());
        if (h.getDisciplina() != null) {
            dto.setDisciplinaId(h.getDisciplina().getIdDisciplina());
            dto.setDisciplinaNome(h.getDisciplina().getNome());
        }
        if (h.getDocente() != null) {
            dto.setDocenteId(h.getDocente().getId());
            dto.setDocenteNome(h.getDocente().getNome());
        }
        if (h.getTurma() != null) {
            dto.setTurmaId(h.getTurma().getIdTurma());
            dto.setTurmaAno(h.getTurma().getAno());
            dto.setTurmaSemestre(h.getTurma().getSemestre());
            dto.setTurmaTurno(h.getTurma().getTurno());
        }
        dto.setDataAlocacao(h.getDataAlocacao());
        return dto;
    }
}
