package com.reto.pruebatecnicalogistica.repositorios;

import com.reto.pruebatecnicalogistica.entidades.Puerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuertoRepository extends JpaRepository<Puerto,Long> {
}
