package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.config.exceptions.BadRequestException;
import com.reto.pruebatecnicalogistica.config.exceptions.NotFoundException;
import com.reto.pruebatecnicalogistica.entidades.Puerto;
import com.reto.pruebatecnicalogistica.repositorios.PuertoRepository;
import com.reto.pruebatecnicalogistica.servicios.interfaces.IPuertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuertoService implements IPuertoService {
    @Autowired
    private PuertoRepository puertoRepository;
    @Override
    public List<Puerto> ObtenerPuertos() {
        return puertoRepository.findAll();
    }

    @Override
    public Optional<Puerto> ObtenerPuertoPorId(Long id) {
        Optional<Puerto> puerto = puertoRepository.findById(id);
        if(puerto.isEmpty()) throw new NotFoundException("Puerto no encontrado");
        return puerto;
    }

    @Override
    public Puerto CrearPuerto(Puerto puerto) {
        if(puerto.getUbicacion() == null || puerto.getTelefono()==null){
            throw new BadRequestException("Datos incompletos (obligatorios: ubicación y Teléfono)");
        }
        return puertoRepository.save(puerto);
    }

    @Override
    public Puerto ActualizarPuerto(Long id, Puerto puerto) {
        Optional<Puerto> puertoActualizar = puertoRepository.findById(id);
        if(puertoActualizar.isEmpty()) throw new NotFoundException("Puerto no encontrado");
        return puertoRepository.save(puerto);
    }

    @Override
    public void EliminarPuerto(Long id) {
        Optional<Puerto> puerto = puertoRepository.findById(id);
        if(puerto.isEmpty()) throw new NotFoundException("Puerto no encontrado");
        puertoRepository.deleteById(id);
    }
}
