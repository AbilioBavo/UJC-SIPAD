package com.bavo.UJC_SIPAD.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "turmas")
    private List<Disciplina> disciplinas;
    @ManyToOne
@JoinColumn(name = "curso_id")
private Curso curso;
}