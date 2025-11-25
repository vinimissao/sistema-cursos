package br.com.escola.config;

import br.com.escola.model.Curso;
import br.com.escola.model.Usuario;
import br.com.escola.repository.CursoRepository;
import br.com.escola.repository.UsuarioRepository;
import br.com.escola.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void run(String... args) throws Exception {
		// Criar usuário admin padrão se não existir
		if (!usuarioService.existePorUsername("admin")) {
			Usuario admin = new Usuario();
			admin.setUsername("admin");
			admin.setSenha("admin123");
			admin.setNome("Administrador");
			admin.setRole("ROLE_ADMIN");
			admin.setAtivo(true);
			usuarioService.salvar(admin);
			System.out.println("Usuário admin criado: admin / admin123");
		}
		
		// Criar cursos de exemplo se não existirem
		if (cursoRepository.count() == 0) {
			Curso curso1 = new Curso();
			curso1.setNome("Java para Iniciantes");
			curso1.setDescricao("Aprenda os fundamentos da programação Java, incluindo sintaxe, orientação a objetos e estruturas de dados.");
			curso1.setCargaHoraria(40);
			curso1.setCategoria("Programação");
			curso1.setPreco(299.90);
			cursoRepository.save(curso1);
			
			Curso curso2 = new Curso();
			curso2.setNome("Spring Boot Avançado");
			curso2.setDescricao("Domine o framework Spring Boot para desenvolvimento de aplicações web robustas e escaláveis.");
			curso2.setCargaHoraria(60);
			curso2.setCategoria("Programação");
			curso2.setPreco(499.90);
			cursoRepository.save(curso2);
			
			Curso curso3 = new Curso();
			curso3.setNome("Design de Interfaces");
			curso3.setDescricao("Aprenda os princípios de design de interfaces modernas e responsivas usando as melhores práticas de UX/UI.");
			curso3.setCargaHoraria(30);
			curso3.setCategoria("Design");
			curso3.setPreco(249.90);
			cursoRepository.save(curso3);
			
			Curso curso4 = new Curso();
			curso4.setNome("Banco de Dados SQL");
			curso4.setDescricao("Compreenda os conceitos fundamentais de bancos de dados relacionais e domine a linguagem SQL.");
			curso4.setCargaHoraria(35);
			curso4.setCategoria("Banco de Dados");
			curso4.setPreco(199.90);
			cursoRepository.save(curso4);
			
			System.out.println("Cursos de exemplo criados!");
		}
	}
}

