package com.api.ExtensaoEscolas.controller;

import com.api.ExtensaoEscolas.dto.DisciplinaDTO;
import com.api.ExtensaoEscolas.model.Disciplina;
import com.api.ExtensaoEscolas.service.DisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DisciplinaController {
    
    private final DisciplinaService disciplinaService;
    
    @GetMapping
    public ResponseEntity<List<Disciplina>> listarTodas() {
        return ResponseEntity.ok(disciplinaService.listarTodas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.buscarPorId(id));
    }
    
    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<Disciplina>> buscarPorProfessor(@PathVariable Long professorId) {
        return ResponseEntity.ok(disciplinaService.buscarPorProfessor(professorId));
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Disciplina>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(disciplinaService.buscarPorNome(nome));
    }
    
    @GetMapping("/pesquisar")
    public ResponseEntity<List<Disciplina>> pesquisar(@RequestParam String termo) {
        return ResponseEntity.ok(disciplinaService.buscarPorNomeOuAssuntos(termo));
    }
    
    @PostMapping
    public ResponseEntity<Disciplina> salvar(@Valid @RequestBody DisciplinaDTO disciplinaDTO) {
        return ResponseEntity.ok(disciplinaService.salvar(disciplinaDTO));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long id, @Valid @RequestBody DisciplinaDTO disciplinaDTO) {
        return ResponseEntity.ok(disciplinaService.atualizar(id, disciplinaDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        disciplinaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}