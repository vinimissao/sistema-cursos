package br.com.escola.service;

import br.com.escola.model.Usuario;
import br.com.escola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Optional<Usuario> buscarPorUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}
	
	public Usuario salvar(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
	
	public Boolean existePorUsername(String username) {
		return usuarioRepository.existsByUsername(username);
	}
}

