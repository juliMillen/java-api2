package com.example.apirest.ApiRest.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirest.ApiRest.Entities.Producto;
import com.example.apirest.ApiRest.Services.ProductoService;

@RestController
@RequestMapping("/productos/")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/")
	public List<Producto> listarProductos(){
		return (List<Producto>)productoService.listaProductos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> buscarPorId(@PathVariable Long id){
		if( id <= 0) {
			return ResponseEntity.badRequest().build();
		}
		return productoService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping("/crear")
	public ResponseEntity<String> CrearProducto(@RequestBody Producto prod)
	{
		try {
			  productoService.crearProducto(prod);
			  return ResponseEntity.ok("Producto creado correctamente");
			}catch(Exception ex) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario");
			}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void eliminarProductoPorId(@PathVariable Long id) {
		  productoService.eliminarPorId(id);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> actualizarProducto(@PathVariable Long id, @RequestBody Producto prod) {	
		try {
			productoService.actualizarProducto(id, prod);
			return ResponseEntity.ok("Producto actualizado correctamente");
		}catch(RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}
}
