package com.bavo.UJC_SIPAD.dto.response;

import com.bavo.UJC_SIPAD.model.Curso;
import lombok.Data;

@Data
public class CursoResponseDTO {
    private Long id;
    private String nome;

    public static CursoResponseDTO fromEntity(Curso curso) {
        CursoResponseDTO dto = new CursoResponseDTO();
        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        return dto;
    }
}
