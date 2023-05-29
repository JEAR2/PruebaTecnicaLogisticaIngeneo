package com.reto.pruebatecnicalogistica.servicios.implementacion;


import com.reto.pruebatecnicalogistica.config.exceptions.BadRequestException;
import com.reto.pruebatecnicalogistica.config.exceptions.NotFoundException;
import com.reto.pruebatecnicalogistica.entidades.LogisticaTerrestre;
import com.reto.pruebatecnicalogistica.repositorios.LogisticaTerrestreRepository;
import com.reto.pruebatecnicalogistica.servicios.interfaces.ILogisticaTerrestreService;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogisticaTerrestreService implements ILogisticaTerrestreService {
    @Autowired
    private LogisticaTerrestreRepository logisticaTerrestreRepository;
    @Override
    public List<LogisticaTerrestre> ObtenerLogisticaTerrestre() {

        return logisticaTerrestreRepository.findAll();
    }

    @Override
    public Optional<LogisticaTerrestre> ObtenerLogisticaTerrestrePorId(Long id) {
        Optional<LogisticaTerrestre> logisticaTerrestre =  logisticaTerrestreRepository.findById(id);
        if (logisticaTerrestre.isEmpty()) throw new NotFoundException("Dato no encontrado");
        return logisticaTerrestre;
    }

    @Override
    public LogisticaTerrestre CrearLogisticaTerrestre(LogisticaTerrestre logisticaTerrestre) {


        if(logisticaTerrestre.getBodega()==null || logisticaTerrestre.getCliente()==null
                || logisticaTerrestre.getTipoProducto()==null || logisticaTerrestre.getCantidadProducto()<=0){
            throw new BadRequestException("Logistica terrestre incorrecta o incompleta");
        }
        return logisticaTerrestreRepository.save(logisticaTerrestre);
    }

    @Override
    public LogisticaTerrestre ActualizarLogisticaTerrestre(Long id, LogisticaTerrestre logisticaTerrestre) {
        Optional<LogisticaTerrestre> logisticaTerrestreActualizar =  logisticaTerrestreRepository.findById(id);
        if (logisticaTerrestreActualizar.isEmpty()) throw new NotFoundException("Dato no encontrado");
        return logisticaTerrestreRepository.save(logisticaTerrestre);
    }

    @Override
    public void EliminarLogisticaTerrestre(Long id) {
        Optional<LogisticaTerrestre> logisticaTerrestre=  logisticaTerrestreRepository.findById(id);
        if (logisticaTerrestre.isEmpty()) throw new NotFoundException("Dato no encontrado");
        logisticaTerrestreRepository.deleteById(id);
    }
}
