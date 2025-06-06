package com.bavo.UJC_SIPAD.service;

import com.bavo.UJC_SIPAD.dto.request.DisciplinaRequestDTO;
import com.bavo.UJC_SIPAD.dto.response.DisciplinaResponseDTO;
import com.bavo.UJC_SIPAD.model.Disciplina;
import com.bavo.UJC_SIPAD.model.Docente;
import com.bavo.UJC_SIPAD.model.DisciplinaDocenteHistorico;
import com.bavo.UJC_SIPAD.repository.DisciplinaRepository;
import com.bavo.UJC_SIPAD.repository.DisciplinaDocenteHistoricoRepository;
import com.bavo.UJC_SIPAD.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private DisciplinaDocenteHistoricoRepository historicoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    public List<DisciplinaResponseDTO> listarTodos() {
        return repository.findAll().stream()
            .map(DisciplinaResponseDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public DisciplinaResponseDTO salvar(DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.getNome());
        disciplina.setCargaHoraria(dto.getCargaHoraria());
        return DisciplinaResponseDTO.fromEntity(repository.save(disciplina));
    }

    public DisciplinaResponseDTO buscarPorId(Long id) {
        Optional<Disciplina> disciplina = repository.findById(id);
        return disciplina.map(DisciplinaResponseDTO::fromEntity).orElse(null);
    }

    public DisciplinaResponseDTO atualizar(Long id, DisciplinaRequestDTO dto) {
        Optional<Disciplina> disciplinaOpt = repository.findById(id);
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            disciplina.setNome(dto.getNome());
            disciplina.setCargaHoraria(dto.getCargaHoraria());
            return DisciplinaResponseDTO.fromEntity(repository.save(disciplina));
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

    public DisciplinaResponseDTO alocarDocente(Long disciplinaId, Long docenteId) {
        Optional<Disciplina> disciplinaOpt = repository.findById(disciplinaId);
        if (disciplinaOpt.isEmpty()) return null;
        Disciplina disciplina = disciplinaOpt.get();
        Docente novoDocente = null;
        if (docenteId != null) {
            Optional<Docente> docenteOpt = docenteRepository.findById(docenteId);
            if (docenteOpt.isEmpty()) return null;
            novoDocente = docenteOpt.get();
        }
        // Se já existe um docente, salva no histórico
        if (disciplina.getDocente() != null) {
            DisciplinaDocenteHistorico hist = new DisciplinaDocenteHistorico();
            hist.setDisciplina(disciplina);
            hist.setDocente(disciplina.getDocente());
            hist.setDataAlocacao(java.time.LocalDateTime.now());
            historicoRepository.save(hist);
        }
        disciplina.setDocente(novoDocente);
        return DisciplinaResponseDTO.fromEntity(repository.save(disciplina));
    }
}