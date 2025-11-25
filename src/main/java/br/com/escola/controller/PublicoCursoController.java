package br.com.escola.controller;

import br.com.escola.model.Curso;
import br.com.escola.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cursos/publico")
public class PublicoCursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public String listarPublico(@RequestParam(required = false) String categoria,
	                            @RequestParam(required = false) String busca,
	                            Model model) {
		List<Curso> cursos;
		
		if (categoria != null && !categoria.isEmpty()) {
			cursos = cursoService.buscarPorCategoria(categoria);
		} else if (busca != null && !busca.isEmpty()) {
			cursos = cursoService.buscarPorNome(busca);
		} else {
			cursos = cursoService.listarTodos();
		}
		
		model.addAttribute("cursos", cursos);
		return "publico/lista-cursos";
	}
	
	@GetMapping("/{id}")
	public String detalhes(@PathVariable Long id, Model model) {
		return cursoService.buscarPorId(id)
			.map(curso -> {
				model.addAttribute("curso", curso);
				return "publico/detalhes-curso";
			})
			.orElse("redirect:/cursos/publico");
	}
}

