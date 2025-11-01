package com.api.ExtensaoEscolas.service;

import com.api.ExtensaoEscolas.dto.ProfessorDTO;
import com.api.ExtensaoEscolas.model.Professor;
import com.api.ExtensaoEscolas.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    
    private final ProfessorRepository professorRepository;
    
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }
    
    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }
    
    public Professor salvar(ProfessorDTO professorDTO) {
        // Verificar se email já existe
        if (professorRepository.existsByEmail(professorDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setEmail(professorDTO.getEmail());
        professor.setSenha(professorDTO.getSenha()); // Na prática, criptografar essa senha
        professor.setDataNascimento(professorDTO.getDataNascimento());
        professor.setEspecialidade(professorDTO.getEspecialidade());
        
        return professorRepository.save(professor);
    }
    
    public Professor atualizar(Long id, ProfessorDTO professorDTO) {
        Professor professor = buscarPorId(id);
        
        // Verificar se outro professor já usa o email
        if (!professor.getEmail().equals(professorDTO.getEmail()) && 
            professorRepository.existsByEmail(professorDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        professor.setNome(professorDTO.getNome());
        professor.setEmail(professorDTO.getEmail());
        professor.setDataNascimento(professorDTO.getDataNascimento());
        professor.setEspecialidade(professorDTO.getEspecialidade());
        
        // Só atualiza a senha se foi fornecida uma nova
        if (professorDTO.getSenha() != null && !professorDTO.getSenha().isEmpty()) {
            professor.setSenha(professorDTO.getSenha());
        }
        
        return professorRepository.save(professor);
    }
    
    public void excluir(Long id) {
        Professor professor = buscarPorId(id);
        professorRepository.delete(professor);
    }
}