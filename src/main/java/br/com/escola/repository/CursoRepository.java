package br.com.escola.repository;

import br.com.escola.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	List<Curso> findByCategoria(String categoria);
	
	List<Curso> findByNomeContainingIgnoreCase(String nome);
}

