package com.bavo.UJC_SIPAD.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurma;
    
    private String turno;
    private String ano;
    private String semestre;
}