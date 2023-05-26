package com.reto.pruebatecnicalogistica.repositorios;

import com.reto.pruebatecnicalogistica.entidades.LogisticaTerrestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticaTerrestreRepository extends JpaRepository<LogisticaTerrestre,Long> {
}
