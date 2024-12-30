package com.example.apirest.ApiRest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirest.ApiRest.Entities.Rol;
import com.example.apirest.ApiRest.Services.RolService;

@RestController
@RequestMapping("/roles")
public class RolController {

	@Autowired
	private RolService rolService;
	
	@GetMapping("/{rolename}")
	public ResponseEntity<Rol> obtenerRol(@PathVariable String roleName){
		try {
			Rol rol = rolService.obtenerRolPorNombre(roleName);
			return ResponseEntity.ok(rol);
			
		}catch(RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<String> crearRol(@RequestBody Rol rol){
		try {
			rolService.guardarRol(rol);
			return ResponseEntity.status(HttpStatus.CREATED).body("Rol creado correctamente");
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido crear");
		}
	}
}
