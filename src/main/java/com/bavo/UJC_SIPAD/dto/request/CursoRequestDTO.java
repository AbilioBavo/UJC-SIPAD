package com.bavo.UJC_SIPAD.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CursoRequestDTO {
    @NotBlank(message = "O nome do curso é obrigatório.")
    private String nome;
}
