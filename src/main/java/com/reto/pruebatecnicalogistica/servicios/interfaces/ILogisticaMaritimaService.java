package com.reto.pruebatecnicalogistica.servicios.interfaces;



import com.reto.pruebatecnicalogistica.entidades.LogisticaMaritima;

import java.util.List;
import java.util.Optional;

public interface ILogisticaMaritimaService {
    List<LogisticaMaritima> ObtenerLogisticaMaritima();
    Optional<LogisticaMaritima> ObtenerLogisticaMaritimaPorId(Long id);
    LogisticaMaritima CrearLogisticaMaritima(LogisticaMaritima logisticaMaritima);
    LogisticaMaritima ActualizarLogisticaMaritima(Long id, LogisticaMaritima logisticaMaritima) ;
    void EliminarLogisticaMaritima(Long id);
}
