package com.example.apirest.ApiRest.Security;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.apirest.ApiRest.Entities.Usuario;
import com.example.apirest.ApiRest.Repositories.IUsuarioRepository;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(nombre)
				.orElseThrow( () -> new UsernameNotFoundException("Usuario no encontrado", null));

		//Creamos una lista de roles para que Spring Security lo reconozca correctamente
		List<SimpleGrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getRoleName())) //Agregar "Role_" al rol
				.collect(Collectors.toList());
		
		// Retornar el usuario con sus roles
		return new User(usuario.getUsername(), usuario.getContra(), authorities);
	}

}
/*
return User.builder()
		.username(usuario.getUsername())
		.password(usuario.getContra())
		.roles("USER")
		.build();*/
