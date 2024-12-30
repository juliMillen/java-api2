package com.example.apirest.ApiRest.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apirest.ApiRest.Entities.Usuario;
import com.example.apirest.ApiRest.Repositories.IUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void registrarUsuario(Usuario usuario) {
		usuario.setContra(passwordEncoder.encode(usuario.getContra()));
		usuarioRepository.save(usuario);
	}
	
	public Usuario obtenerUsuarioPorUsername(String nombre) {
	  Optional<Usuario> usuario = usuarioRepository.findByUsername(nombre);
	  return usuario.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}
}
