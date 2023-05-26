package com.reto.pruebatecnicalogistica.repositorios;

import com.reto.pruebatecnicalogistica.entidades.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {}
