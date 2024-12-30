package com.example.apirest.ApiRest.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apirest.ApiRest.Entities.Producto;
import com.example.apirest.ApiRest.Repositories.IProductoRepository;

@Service
public class ProductoService{

	@Autowired
	private IProductoRepository productoRepository;
	
	public List<Producto> listaProductos(){
		return productoRepository.findAll();
	}
	
	public Optional<Producto> obtenerPorId(Long id){
		return productoRepository.findById(id);
	}
	
	public void crearProducto(Producto prod) {
		productoRepository.save(prod);
	}
	
	public void eliminarPorId(Long id) {
		productoRepository.deleteById(id);
	}
	
	public void actualizarProducto(Long id, Producto prod) {
		Optional<Producto> productoExistente = productoRepository.findById(id);
		if(productoExistente.isPresent()) {
			Producto productoActualizado = productoExistente.get();
			productoActualizado.setNombre(prod.getNombre());
			productoActualizado.setPrecio(prod.getPrecio());
			
			productoRepository.save(productoActualizado);
		}else {
			throw new RuntimeException("Producto no encontrado");
		}
	}
}
