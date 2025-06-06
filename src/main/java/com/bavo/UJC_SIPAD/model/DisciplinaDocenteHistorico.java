package com.bavo.UJC_SIPAD.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class DisciplinaDocenteHistorico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Disciplina disciplina;

    @ManyToOne
    private Docente docente;

    @ManyToOne
    private Turma turma;

    private LocalDateTime dataAlocacao;
}
