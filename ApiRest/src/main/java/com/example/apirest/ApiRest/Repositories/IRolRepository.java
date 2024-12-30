package com.example.apirest.ApiRest.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apirest.ApiRest.Entities.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{

	Optional<Rol> findByRoleName(String roleName);
}