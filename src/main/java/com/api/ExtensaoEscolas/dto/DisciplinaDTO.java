package com.api.ExtensaoEscolas.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DisciplinaDTO {
    
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome deve conter apenas letras")
    private String nome;
    
    @NotBlank(message = "Carga horária é obrigatória")
    @Pattern(regexp = "^\\d+$", message = "Carga horária deve conter apenas números")
    private String cargaHoraria;
    
    @NotBlank(message = "Assuntos são obrigatórios")
    private String assuntos;
    
    private Long professorId;
}