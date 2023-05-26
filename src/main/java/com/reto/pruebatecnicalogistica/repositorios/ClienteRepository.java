package com.reto.pruebatecnicalogistica.repositorios;

import com.reto.pruebatecnicalogistica.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
