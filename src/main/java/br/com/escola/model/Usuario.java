package br.com.escola.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Username é obrigatório")
	@Size(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
	@Column(nullable = false, unique = true, length = 50)
	private String username;
	
	@NotBlank(message = "Senha é obrigatória")
	@Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
	@Column(nullable = false)
	private String senha;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false)
	private Boolean ativo = true;
	
	@Column(nullable = false)
	private String role = "ROLE_ADMIN";
	
	public Usuario() {
	}
	
	public Usuario(String username, String senha, String nome) {
		this.username = username;
		this.senha = senha;
		this.nome = nome;
	}
	
	// Getters e Setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}

