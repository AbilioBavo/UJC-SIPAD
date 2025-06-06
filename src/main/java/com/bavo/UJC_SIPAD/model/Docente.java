package com.bavo.UJC_SIPAD.model;

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
}
