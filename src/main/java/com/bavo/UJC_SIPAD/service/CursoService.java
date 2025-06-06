package com.bavo.UJC_SIPAD.service;

import com.bavo.UJC_SIPAD.dto.request.CursoRequestDTO;
import com.bavo.UJC_SIPAD.dto.response.CursoResponseDTO;
import com.bavo.UJC_SIPAD.model.Curso;
import com.bavo.UJC_SIPAD.model.Disciplina;
import com.bavo.UJC_SIPAD.repository.CursoRepository;
import com.bavo.UJC_SIPAD.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public Curso salvar(Curso curso) {
        return repository.save(curso);
    }

    public List<CursoResponseDTO> listarTodosDTO() {
        return repository.findAll().stream()
            .map(CursoResponseDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public CursoResponseDTO salvarDTO(CursoRequestDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        return CursoResponseDTO.fromEntity(repository.save(curso));
    }

    public CursoResponseDTO buscarPorIdDTO(Long id) {
        Optional<Curso> curso = repository.findById(id);
        return curso.map(CursoResponseDTO::fromEntity).orElse(null);
    }

    public CursoResponseDTO atualizarDTO(Long id, CursoRequestDTO dto) {
        Optional<Curso> cursoOpt = repository.findById(id);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            curso.setNome(dto.getNome());
            return CursoResponseDTO.fromEntity(repository.save(curso));
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

    public CursoResponseDTO associarDisciplinas(Long cursoId, List<Long> disciplinaIds) {
        Optional<Curso> cursoOpt = repository.findById(cursoId);
        if (cursoOpt.isEmpty()) return null;
        Curso curso = cursoOpt.get();
        List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinaIds);
        curso.setDisciplinas(disciplinas);
        // Atualiza o lado proprietário do relacionamento
        for (Disciplina d : disciplinas) {
            List<Curso> cursos = d.getCursos();
            if (cursos == null) cursos = new ArrayList<>();
            if (!cursos.contains(curso)) {
                cursos.add(curso);
                d.setCursos(cursos);
                disciplinaRepository.save(d);
            }
        }
        return CursoResponseDTO.fromEntity(repository.save(curso));
    }
}
