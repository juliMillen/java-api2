package com.example.apirest.ApiRest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apirest.ApiRest.Entities.Rol;
import com.example.apirest.ApiRest.Repositories.IRolRepository;

@Service
public class RolService {
	@Autowired
	private IRolRepository rolRepository;
	
	public Rol obtenerRolPorNombre(String rolName) {
		return rolRepository.findByRoleName(rolName).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
	}
	
	public void guardarRol(Rol rol)
	{
		rolRepository.save(rol);
	}
}