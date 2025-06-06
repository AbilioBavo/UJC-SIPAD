package com.bavo.UJC_SIPAD.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDisciplina;
    
    private String nome;
    private String cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
}