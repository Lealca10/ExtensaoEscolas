package com.api.ExtensaoEscolas.controller;

import com.api.ExtensaoEscolas.dto.ProfessorDTO;
import com.api.ExtensaoEscolas.model.Professor;
import com.api.ExtensaoEscolas.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Para conectar com seu frontend
public class ProfessorController {
    
    private final ProfessorService professorService;
    
    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        return ResponseEntity.ok(professorService.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.buscarPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<Professor> salvar(@Valid @RequestBody ProfessorDTO professorDTO) {
        return ResponseEntity.ok(professorService.salvar(professorDTO));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @Valid @RequestBody ProfessorDTO professorDTO) {
        return ResponseEntity.ok(professorService.atualizar(id, professorDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        professorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}