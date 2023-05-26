package com.reto.pruebatecnicalogistica.servicios.interfaces;


import com.reto.pruebatecnicalogistica.entidades.LogisticaTerrestre;

import java.util.List;
import java.util.Optional;

public interface ILogisticaTerrestreService {
    List<LogisticaTerrestre> ObtenerLogisticaTerrestre();
    Optional<LogisticaTerrestre> ObtenerLogisticaTerrestrePorId(Long id);
    LogisticaTerrestre CrearLogisticaTerrestre(LogisticaTerrestre logisticaTerrestre);
    LogisticaTerrestre ActualizarLogisticaTerrestre(Long id, LogisticaTerrestre logisticaTerrestre) ;
    void EliminarLogisticaTerrestre(Long id);
}
