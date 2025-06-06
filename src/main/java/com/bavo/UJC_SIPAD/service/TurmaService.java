package com.bavo.UJC_SIPAD.service;

import com.bavo.UJC_SIPAD.dto.request.TurmaRequestDTO;
import com.bavo.UJC_SIPAD.dto.response.TurmaResponseDTO;
import com.bavo.UJC_SIPAD.model.Disciplina;
import com.bavo.UJC_SIPAD.model.Turma;
import com.bavo.UJC_SIPAD.repository.DisciplinaRepository;
import com.bavo.UJC_SIPAD.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository repository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<TurmaResponseDTO> listarTodos() {
        return repository.findAll().stream()
            .map(TurmaResponseDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public TurmaResponseDTO salvar(TurmaRequestDTO dto) {
        Turma turma = new Turma();
        turma.setTurno(dto.getTurno());
        turma.setAno(dto.getAno());
        turma.setSemestre(dto.getSemestre());
        return TurmaResponseDTO.fromEntity(repository.save(turma));
    }

    public TurmaResponseDTO buscarPorId(Long id) {
        Optional<Turma> turma = repository.findById(id);
        return turma.map(TurmaResponseDTO::fromEntity).orElse(null);
    }

    public TurmaResponseDTO atualizar(Long id, TurmaRequestDTO dto) {
        Optional<Turma> turmaOpt = repository.findById(id);
        if (turmaOpt.isPresent()) {
            Turma turma = turmaOpt.get();
            turma.setTurno(dto.getTurno());
            turma.setAno(dto.getAno());
            turma.setSemestre(dto.getSemestre());
            return TurmaResponseDTO.fromEntity(repository.save(turma));
        }
        return null;
    }

    public boolean apagarPorId(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public TurmaResponseDTO associarDisciplinas(Long turmaId, List<Long> disciplinaIds) {
        Optional<Turma> turmaOpt = repository.findById(turmaId);
        if (turmaOpt.isEmpty()) return null;
        Turma turma = turmaOpt.get();
        List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinaIds);
        turma.setDisciplinas(disciplinas);
        // Atualiza o lado proprietário do relacionamento
        for (Disciplina d : disciplinas) {
            List<Turma> turmas = d.getTurmas();
            if (turmas == null) turmas = new java.util.ArrayList<>();
            if (!turmas.contains(turma)) {
                turmas.add(turma);
                d.setTurmas(turmas);
                disciplinaRepository.save(d);
            }
        }
        return TurmaResponseDTO.fromEntity(repository.save(turma));
    }
}