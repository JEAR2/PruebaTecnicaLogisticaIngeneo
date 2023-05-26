package com.reto.pruebatecnicalogistica.servicios.interfaces;



import com.reto.pruebatecnicalogistica.entidades.Puerto;

import java.util.List;
import java.util.Optional;

public interface IPuertoService {
    List<Puerto> ObtenerPuertos();
    Optional<Puerto> ObtenerPuertoPorId(Long id);
    Puerto CrearPuerto(Puerto puerto);
    Puerto ActualizarPuerto(Long id, Puerto puerto) ;
    void EliminarPuerto(Long id);
}
