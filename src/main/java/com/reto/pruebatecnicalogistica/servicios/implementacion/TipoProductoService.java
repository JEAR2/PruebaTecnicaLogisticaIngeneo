package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.config.exceptions.BadRequestException;
import com.reto.pruebatecnicalogistica.config.exceptions.NotFoundException;
import com.reto.pruebatecnicalogistica.entidades.TipoProducto;
import com.reto.pruebatecnicalogistica.repositorios.TipoProductoRepository;
import com.reto.pruebatecnicalogistica.servicios.interfaces.ITipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoProductoService implements ITipoProductoService {
    @Autowired
    private TipoProductoRepository tipoProductoRepository;
    @Override
    public List<TipoProducto> ObtenerTiposProducto() {

        return tipoProductoRepository.findAll();
    }

    @Override
    public Optional<TipoProducto> ObtenerTipoProductoPorId(Long id) {
        Optional<TipoProducto> tipoProducto = tipoProductoRepository.findById(id);
        if(tipoProducto.isEmpty()) throw new NotFoundException("Tipo de Producto no encontrado");
        return tipoProducto;
    }

    @Override
    public TipoProducto CrearTipoProducto(TipoProducto tipoProducto) {
        if(tipoProducto.getNombre() == null) throw new BadRequestException("Nombre obligatorio");
        return tipoProductoRepository.save(tipoProducto);
    }

    @Override
    public TipoProducto ActualizarTipoProducto(Long id, TipoProducto tipoProducto) {
        Optional<TipoProducto> tipoProductoActualizar = tipoProductoRepository.findById(id);
        if(tipoProductoActualizar.isEmpty()) throw new NotFoundException("Tipo de Producto no encontrado");
        return tipoProductoRepository.save(tipoProducto);
    }

    @Override
    public void EliminarTipoProducto(Long id) {
        Optional<TipoProducto> tipoProducto = tipoProductoRepository.findById(id);
        if(tipoProducto.isEmpty()) throw new NotFoundException("Tipo de Producto no encontrado");
        tipoProductoRepository.deleteById(id);
    }
}
