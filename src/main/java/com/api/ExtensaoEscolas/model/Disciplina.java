package com.api.ExtensaoEscolas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "disciplinas")
public class Disciplina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome deve conter apenas letras")
    @Column(nullable = false)
    private String nome;
    
    @NotBlank(message = "Carga horária é obrigatória")
    @Pattern(regexp = "^\\d+$", message = "Carga horária deve conter apenas números")
    @Column(name = "carga_horaria", nullable = false)
    private String cargaHoraria;
    
    @NotBlank(message = "Assuntos são obrigatórios")
    @Column(columnDefinition = "TEXT")
    private String assuntos;
    
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
