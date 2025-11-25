package br.com.escola.controller;

import br.com.escola.model.Curso;
import br.com.escola.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/cursos")
public class ApiCursoController {
	
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
	
	@PostMapping
	public ResponseEntity<?> criar(@Valid @RequestBody Curso curso, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Erro de validação: " + result.getAllErrors());
		}
		
		try {
			Curso cursoSalvo = cursoService.salvar(curso);
			return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erro ao criar curso: " + e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
	                                  @Valid @RequestBody Curso curso, 
	                                  BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Erro de validação: " + result.getAllErrors());
		}
		
		try {
			Curso cursoAtualizado = cursoService.atualizar(id, curso);
			return ResponseEntity.ok(cursoAtualizado);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erro ao atualizar curso: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			cursoService.deletar(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erro ao deletar curso: " + e.getMessage());
		}
	}
}

