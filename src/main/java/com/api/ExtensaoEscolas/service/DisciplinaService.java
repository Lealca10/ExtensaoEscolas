package com.api.ExtensaoEscolas.service;

import com.api.ExtensaoEscolas.dto.DisciplinaDTO;
import com.api.ExtensaoEscolas.model.Disciplina;
import com.api.ExtensaoEscolas.model.Professor;
import com.api.ExtensaoEscolas.repository.DisciplinaRepository;
import com.api.ExtensaoEscolas.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {
    
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;
    
    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }
    
    public Disciplina buscarPorId(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }
    
    public List<Disciplina> buscarPorProfessor(Long professorId) {
        return disciplinaRepository.findByProfessorId(professorId);
    }
    
    public List<Disciplina> buscarPorNome(String nome) {
        return disciplinaRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    public List<Disciplina> buscarPorNomeOuAssuntos(String termo) {
        return disciplinaRepository.buscarPorNomeOuAssuntos(termo);
    }
    
    public Disciplina salvar(DisciplinaDTO disciplinaDTO) {
        // Validar se já existe disciplina com mesmo nome para o professor
        if (disciplinaDTO.getProfessorId() != null && 
            disciplinaRepository.existsByNomeAndProfessorId(disciplinaDTO.getNome(), disciplinaDTO.getProfessorId())) {
            throw new RuntimeException("Já existe uma disciplina com este nome para este professor");
        }
        
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setCargaHoraria(disciplinaDTO.getCargaHoraria());
        disciplina.setAssuntos(disciplinaDTO.getAssuntos());
        
        // Associar professor se fornecido
        if (disciplinaDTO.getProfessorId() != null) {
            Professor professor = professorRepository.findById(disciplinaDTO.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            disciplina.setProfessor(professor);
        }
        
        return disciplinaRepository.save(disciplina);
    }
    
    public Disciplina atualizar(Long id, DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = buscarPorId(id);
        
        // Validar se outro professor já tem disciplina com mesmo nome
        if (disciplinaDTO.getProfessorId() != null && 
            disciplinaRepository.existsByNomeAndProfessorId(disciplinaDTO.getNome(), disciplinaDTO.getProfessorId()) &&
            !disciplina.getProfessor().getId().equals(disciplinaDTO.getProfessorId())) {
            throw new RuntimeException("Já existe uma disciplina com este nome para este professor");
        }
        
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setCargaHoraria(disciplinaDTO.getCargaHoraria());
        disciplina.setAssuntos(disciplinaDTO.getAssuntos());
        
        // Atualizar professor se fornecido
        if (disciplinaDTO.getProfessorId() != null) {
            Professor professor = professorRepository.findById(disciplinaDTO.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            disciplina.setProfessor(professor);
        }
        
        return disciplinaRepository.save(disciplina);
    }
    
    public void excluir(Long id) {
        Disciplina disciplina = buscarPorId(id);
        disciplinaRepository.delete(disciplina);
    }
}