package com.bavo.UJC_SIPAD.service;

import com.bavo.UJC_SIPAD.dto.request.DisciplinaRequestDTO;
import com.bavo.UJC_SIPAD.dto.response.DisciplinaResponseDTO;
import com.bavo.UJC_SIPAD.model.Disciplina;
import com.bavo.UJC_SIPAD.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;

    public List<Disciplina> listarTodos() {
        return repository.findAll();
    }

    public Disciplina salvar(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    public List<DisciplinaResponseDTO> listarTodosDTO() {
        return repository.findAll().stream()
            .map(DisciplinaResponseDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public DisciplinaResponseDTO salvarDTO(DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.getNome());
        disciplina.setCargaHoraria(dto.getCargaHoraria());
        return DisciplinaResponseDTO.fromEntity(repository.save(disciplina));
    }

    public DisciplinaResponseDTO buscarPorIdDTO(Long id) {
        Optional<Disciplina> disciplina = repository.findById(id);
        return disciplina.map(DisciplinaResponseDTO::fromEntity).orElse(null);
    }

    public DisciplinaResponseDTO atualizarDTO(Long id, DisciplinaRequestDTO dto) {
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
}