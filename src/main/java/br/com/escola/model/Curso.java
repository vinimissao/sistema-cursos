package br.com.escola.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
	@Column(nullable = false, length = 100)
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória")
	@Size(min = 10, max = 500, message = "Descrição deve ter entre 10 e 500 caracteres")
	@Column(nullable = false, length = 500)
	private String descricao;
	
	@NotNull(message = "Carga horária é obrigatória")
	@Positive(message = "Carga horária deve ser positiva")
	@Column(nullable = false)
	private Integer cargaHoraria;
	
	@NotBlank(message = "Categoria é obrigatória")
	@Size(min = 2, max = 50, message = "Categoria deve ter entre 2 e 50 caracteres")
	@Column(nullable = false, length = 50)
	private String categoria;
	
	@NotNull(message = "Preço é obrigatório")
	@Positive(message = "Preço deve ser positivo")
	@Column(nullable = false)
	private Double preco;
	
	public Curso() {
	}
	
	public Curso(String nome, String descricao, Integer cargaHoraria, String categoria, Double preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.cargaHoraria = cargaHoraria;
		this.categoria = categoria;
		this.preco = preco;
	}
	
	// Getters e Setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
}

