package br.com.escola.controller;

import br.com.escola.model.Curso;
import br.com.escola.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/cursos")
public class WebCursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("cursos", cursoService.listarTodos());
		return "admin/lista-cursos";
	}
	
	@GetMapping("/novo")
	public String novoCurso(Model model) {
		model.addAttribute("curso", new Curso());
		return "admin/form-curso";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCurso(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		return cursoService.buscarPorId(id)
			.map(curso -> {
				model.addAttribute("curso", curso);
				return "admin/form-curso";
			})
			.orElseGet(() -> {
				redirectAttributes.addFlashAttribute("erro", "Curso não encontrado");
				return "redirect:/admin/cursos";
			});
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid @ModelAttribute Curso curso, BindingResult result, 
	                     RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "admin/form-curso";
		}
		
		try {
			if (curso.getId() == null) {
				cursoService.salvar(curso);
				redirectAttributes.addFlashAttribute("sucesso", "Curso cadastrado com sucesso!");
			} else {
				cursoService.atualizar(curso.getId(), curso);
				redirectAttributes.addFlashAttribute("sucesso", "Curso atualizado com sucesso!");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("erro", "Erro ao salvar curso: " + e.getMessage());
		}
		
		return "redirect:/admin/cursos";
	}
	
	@PostMapping("/deletar/{id}")
	public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			cursoService.deletar(id);
			redirectAttributes.addFlashAttribute("sucesso", "Curso removido com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("erro", "Erro ao remover curso: " + e.getMessage());
		}
		return "redirect:/admin/cursos";
	}
}

