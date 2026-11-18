package com.example.apirest.ApiRest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apirest.ApiRest.Entities.Producto;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
	Optional<Producto> findByNameIgnoreCase(String name);

    List<Producto> findAllByNameIgnoreCase(String name);
}
