package com.reto.pruebatecnicalogistica.servicios.implementacion;


import com.reto.pruebatecnicalogistica.config.exceptions.NotFoundException;
import com.reto.pruebatecnicalogistica.entidades.Bodega;
import com.reto.pruebatecnicalogistica.repositorios.BodegaRepository;
import com.reto.pruebatecnicalogistica.servicios.interfaces.IBodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegaService implements IBodegaService {

    @Autowired
    private BodegaRepository bodegaRepository;

    @Override
    public List<Bodega> ObtenerBodegas() {
        return bodegaRepository.findAll();
    }

    @Override
    public Optional<Bodega> ObtenerBodegaPorId(Long id) {
        Optional<Bodega> bodega =  bodegaRepository.findById(id);
        if (bodega.isEmpty()) throw new NotFoundException("Bodega no encontrada");
        return bodega;
    }

    @Override
    public Bodega CrearBodega(Bodega bodega) {
        if(bodega.getNombre()==null || bodega.getTelefono()==null || bodega.getUbicacion()==null){
            throw new NotFoundException("Datos para bodega incompletos");
        }
        return bodegaRepository.save(bodega);
    }

    @Override
    public Bodega ActualizarBodega(Long id, Bodega bodega) {
        Optional<Bodega> bodegaActualizar =  bodegaRepository.findById(id);
        if (bodegaActualizar.isEmpty()) throw new NotFoundException("Bodega no encontrada");
        return bodegaRepository.save(bodega);
    }

    @Override
    public void EliminarBodega(Long id) {
        Optional<Bodega> bodega =  bodegaRepository.findById(id);
        if (bodega.isEmpty()) throw new NotFoundException("Bodega no encontrada");
        bodegaRepository.deleteById(id);
    }
}
