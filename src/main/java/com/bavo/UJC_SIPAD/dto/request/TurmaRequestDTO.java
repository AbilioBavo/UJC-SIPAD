package com.bavo.UJC_SIPAD.dto.request;

import lombok.Data;

@Data
public class TurmaRequestDTO {
    private String turno;
    private String ano;
    private String semestre;
    private Long cursoId;
}