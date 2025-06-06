package com.bavo.UJC_SIPAD.dto.response;

import com.bavo.UJC_SIPAD.model.Docente;
import lombok.Data;

@Data
public class DocenteMinDTO {
    private Long id;
    private String nome;
    private String titulacaoMaxima;

    public static DocenteMinDTO fromEntity(Docente docente) {
        DocenteMinDTO dto = new DocenteMinDTO();
        dto.setId(docente.getId());
        dto.setNome(docente.getNome());
        dto.setTitulacaoMaxima(docente.getTitulacaoMaxima());
        return dto;
    }
}
