package com.example.apirest.ApiRest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apirest.ApiRest.Entities.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{

}
