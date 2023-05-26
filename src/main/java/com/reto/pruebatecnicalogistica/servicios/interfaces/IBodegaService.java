package com.reto.pruebatecnicalogistica.servicios.interfaces;


import com.reto.pruebatecnicalogistica.entidades.Bodega;

import java.util.List;
import java.util.Optional;

public interface IBodegaService {
    List<Bodega> ObtenerBodegas();
    Optional<Bodega> ObtenerBodegaPorId(Long id);
    Bodega CrearBodega(Bodega bodega);
    Bodega ActualizarBodega(Long id, Bodega bodega) ;
    void EliminarBodega(Long id);
}
