package com.example.apirest.ApiRest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirest.ApiRest.Entities.Rol;
import com.example.apirest.ApiRest.Entities.Usuario;
import com.example.apirest.ApiRest.Services.RolService;
import com.example.apirest.ApiRest.Services.UsuarioService;

@RestController
@RequestMapping("/usuarios/")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/{nombre}")
	public ResponseEntity<?> obtenerUsuarioPorUsername(@PathVariable String nombre) {
		try {
			Usuario buscado = usuarioService.obtenerUsuarioPorUsername(nombre);
			return ResponseEntity.ok(buscado);
		}catch(RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Usuario usu){
		try {
			Usuario usuarioExistente = usuarioService.obtenerUsuarioPorUsername(usu.getUsername());
			if(usuarioExistente != null && passwordEncoder.matches(usu.getContra(), usuarioExistente.getContra())) {
				return ResponseEntity.ok("Login exitoso");
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
			}
		}catch(RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
		}
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
		try {
			usuario.setContra(passwordEncoder.encode(usuario.getContra())); //encripta la contraseña antes de guardar
			
			//Comprobar si el rol "ROLE_ADMIN" ya existe, si no, guardarlo
			Rol rolAdmin = rolService.obtenerRolPorNombre("ROLE_ADMIN");
			if(rolAdmin == null) {
				rolAdmin = new Rol();
				rolAdmin.setRoleName("ROLE_ADMIN");
				rolService.guardarRol(rolAdmin);
			}
			
			//Asginar el rol "ROLE_ADMIN" al usuario
			usuario.getRoles().add(rolAdmin);
			
			usuarioService.registrarUsuario(usuario);
			return ResponseEntity.ok("Usuario registrado correctamente");
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el usuario" + ex.getMessage());
		}
	}
	
	
}
