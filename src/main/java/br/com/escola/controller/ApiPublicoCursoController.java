package br.com.escola.controller;

import br.com.escola.model.Curso;
import br.com.escola.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class ApiPublicoCursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<List<Curso>> listarTodos() {
		List<Curso> cursos = cursoService.listarTodos();
		return ResponseEntity.ok(cursos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
		Optional<Curso> curso = cursoService.buscarPorId(id);
		return curso.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Curso>> buscarPorCategoria(@PathVariable String categoria) {
		List<Curso> cursos = cursoService.buscarPorCategoria(categoria);
		return ResponseEntity.ok(cursos);
	}
	
	@GetMapping("/busca")
	public ResponseEntity<List<Curso>> buscarPorNome(@RequestParam String nome) {
		List<Curso> cursos = cursoService.buscarPorNome(nome);
		return ResponseEntity.ok(cursos);
	}
}

