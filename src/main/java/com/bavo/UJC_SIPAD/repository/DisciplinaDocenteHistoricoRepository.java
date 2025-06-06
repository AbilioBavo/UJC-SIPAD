package com.bavo.UJC_SIPAD.repository;

import com.bavo.UJC_SIPAD.model.DisciplinaDocenteHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DisciplinaDocenteHistoricoRepository extends JpaRepository<DisciplinaDocenteHistorico, Long> {
    @Query("SELECT h FROM DisciplinaDocenteHistorico h WHERE (:disciplinaId IS NULL OR h.disciplina.idDisciplina = :disciplinaId) " +
           "AND (:cursoId IS NULL OR EXISTS (SELECT 1 FROM h.disciplina.cursos c WHERE c.id = :cursoId)) " +
           "AND (:turmaId IS NULL OR h.turma.idTurma = :turmaId) " +
           "AND (:ano IS NULL OR h.turma.ano = :ano) " +
           "AND (:semestre IS NULL OR h.turma.semestre = :semestre)")
    List<DisciplinaDocenteHistorico> findHistorico(
        @Param("disciplinaId") Long disciplinaId,
        @Param("cursoId") Long cursoId,
        @Param("turmaId") Long turmaId,
        @Param("ano") String ano,
        @Param("semestre") String semestre
    );
}
