package com.reto.pruebatecnicalogistica.servicios.interfaces;



import com.reto.pruebatecnicalogistica.entidades.TipoProducto;

import java.util.List;
import java.util.Optional;

public interface ITipoProductoService {
    List<TipoProducto> ObtenerTiposProducto();
    Optional<TipoProducto> ObtenerTipoProductoPorId(Long id);
    TipoProducto CrearTipoProducto(TipoProducto tipoProducto);
    TipoProducto ActualizarTipoProducto(Long id, TipoProducto tipoProducto) ;
    void EliminarTipoProducto(Long id);
}
