package com.bavo.UJC_SIPAD.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String titulacaoMaxima;
    private String endereco;
    private String telefone;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "docente")
    private List<Disciplina> disciplinas;
}
