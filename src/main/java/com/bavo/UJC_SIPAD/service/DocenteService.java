package com.bavo.UJC_SIPAD.service;

import com.bavo.UJC_SIPAD.dto.request.DocenteRequestDTO;
import com.bavo.UJC_SIPAD.dto.response.DocenteMinDTO;
import com.bavo.UJC_SIPAD.dto.response.DocenteResponseDTO;
import com.bavo.UJC_SIPAD.model.Docente;
import com.bavo.UJC_SIPAD.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocenteService {
    @Autowired
    private DocenteRepository repository;

    public List<DocenteMinDTO> listarTodos() {
        return repository.findAll().stream()
            .map(docente -> {
                DocenteMinDTO dto = new DocenteMinDTO();
                dto.setId(docente.getId());
                dto.setNome(docente.getNome());
                dto.setTitulacaoMaxima(docente.getTitulacaoMaxima());
                return dto;
            })
            .collect(Collectors.toList());
    }

    public DocenteResponseDTO buscarPorId(Long id) {
        Optional<Docente> docente = repository.findById(id);
        return docente.map(DocenteResponseDTO::fromEntity).orElse(null);
    }

    public DocenteResponseDTO salvar(DocenteRequestDTO dto) {
        Docente docente = new Docente();
        docente.setNome(dto.getNome());
        docente.setTitulacaoMaxima(dto.getTitulacaoMaxima());
        docente.setEndereco(dto.getEndereco());
        docente.setTelefone(dto.getTelefone());
        docente.setEmail(dto.getEmail());
        return DocenteResponseDTO.fromEntity(repository.save(docente));
    }


    public DocenteResponseDTO atualizar(Long id, DocenteRequestDTO dto) {
        Optional<Docente> docenteOpt = repository.findById(id);
        if (docenteOpt.isPresent()) {
            Docente docente = docenteOpt.get();
            docente.setNome(dto.getNome());
            docente.setTitulacaoMaxima(dto.getTitulacaoMaxima());
            docente.setEndereco(dto.getEndereco());
            docente.setTelefone(dto.getTelefone());
            docente.setEmail(dto.getEmail());
            return DocenteResponseDTO.fromEntity(repository.save(docente));
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
