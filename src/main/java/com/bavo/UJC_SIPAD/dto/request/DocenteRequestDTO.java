package com.bavo.UJC_SIPAD.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocenteRequestDTO {
     @NotBlank(message = "O nome docente é obrigatório.")
    private String nome;
    private String titulacaoMaxima;
    private String endereco;
    private String telefone;
    private String email;
}
