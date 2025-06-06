package com.bavo.UJC_SIPAD.dto.response;

import com.bavo.UJC_SIPAD.model.Docente;
import lombok.Data;

@Data
public class DocenteResponseDTO {
    private Long id;
    private String nome;
    private String titulacaoMaxima;
    private String endereco;
    private String telefone;
    private String email;

    public static DocenteResponseDTO fromEntity(Docente docente) {
        DocenteResponseDTO dto = new DocenteResponseDTO();
        dto.setId(docente.getId());
        dto.setNome(docente.getNome());
        dto.setTitulacaoMaxima(docente.getTitulacaoMaxima());
        dto.setEndereco(docente.getEndereco());
        dto.setTelefone(docente.getTelefone());
        dto.setEmail(docente.getEmail());
        return dto;
    }
}
