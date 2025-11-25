package br.com.escola.service;

import br.com.escola.model.Curso;
import br.com.escola.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	public List<Curso> listarTodos() {
		return cursoRepository.findAll();
	}
	
	public Optional<Curso> buscarPorId(Long id) {
		return cursoRepository.findById(id);
	}
	
	public Curso salvar(Curso curso) {
		return cursoRepository.save(curso);
	}
	
	public Curso atualizar(Long id, Curso cursoAtualizado) {
		Curso curso = cursoRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + id));
		
		curso.setNome(cursoAtualizado.getNome());
		curso.setDescricao(cursoAtualizado.getDescricao());
		curso.setCargaHoraria(cursoAtualizado.getCargaHoraria());
		curso.setCategoria(cursoAtualizado.getCategoria());
		curso.setPreco(cursoAtualizado.getPreco());
		
		return cursoRepository.save(curso);
	}
	
	public void deletar(Long id) {
		if (!cursoRepository.existsById(id)) {
			throw new RuntimeException("Curso não encontrado com id: " + id);
		}
		cursoRepository.deleteById(id);
	}
	
	public List<Curso> buscarPorCategoria(String categoria) {
		return cursoRepository.findByCategoria(categoria);
	}
	
	public List<Curso> buscarPorNome(String nome) {
		return cursoRepository.findByNomeContainingIgnoreCase(nome);
	}
}

