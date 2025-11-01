package com.api.ExtensaoEscolas.repository;

import com.api.ExtensaoEscolas.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    
    List<Disciplina> findByNomeContainingIgnoreCase(String nome);
    
    List<Disciplina> findByProfessorId(Long professorId);
    
    @Query("SELECT d FROM Disciplina d WHERE LOWER(d.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(d.assuntos) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Disciplina> buscarPorNomeOuAssuntos(@Param("termo") String termo);
    
    boolean existsByNomeAndProfessorId(String nome, Long professorId);
}