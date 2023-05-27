package com.reto.pruebatecnicalogistica.security.repositorio;

import com.reto.pruebatecnicalogistica.security.entidad.Rol;
import com.reto.pruebatecnicalogistica.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
