package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.config.exceptions.BadRequestException;
import com.reto.pruebatecnicalogistica.config.exceptions.NotFoundException;
import com.reto.pruebatecnicalogistica.entidades.LogisticaMaritima;
import com.reto.pruebatecnicalogistica.repositorios.LogisticaMaritimaRepository;
import com.reto.pruebatecnicalogistica.servicios.interfaces.ILogisticaMaritimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogisticaMaritimaService implements ILogisticaMaritimaService {
    @Autowired
    private LogisticaMaritimaRepository logisticaMaritimaRepository;
    @Override
    public List<LogisticaMaritima> ObtenerLogisticaMaritima() {

        return logisticaMaritimaRepository.findAll();
    }

    @Override
    public Optional<LogisticaMaritima> ObtenerLogisticaMaritimaPorId(Long id) {
        Optional<LogisticaMaritima> logisticaMaritima =  logisticaMaritimaRepository.findById(id);
        if (logisticaMaritima.isEmpty()) throw new NotFoundException("Dato no encontrado");
        return logisticaMaritima;
    }

    @Override
    public LogisticaMaritima CrearLogisticaMaritima(LogisticaMaritima logisticaMaritima) {

        if(logisticaMaritima.getPuerto()==null || logisticaMaritima.getCliente()==null
                || logisticaMaritima.getTipoProducto()==null || logisticaMaritima.getCantidadProducto()<=0){
            throw new BadRequestException("Logistica maritima incorrecta o incompleta");
        }
        return logisticaMaritimaRepository.save(logisticaMaritima);
    }

    @Override
    public LogisticaMaritima ActualizarLogisticaMaritima(Long id, LogisticaMaritima logisticaMaritima) {
        Optional<LogisticaMaritima> logisticaMaritimaActualizar =  logisticaMaritimaRepository.findById(id);
        if (logisticaMaritimaActualizar.isEmpty()) throw new NotFoundException("Dato no encontrado");
        return logisticaMaritimaRepository.save(logisticaMaritima);
    }

    @Override
    public void EliminarLogisticaMaritima(Long id) {
        Optional<LogisticaMaritima> logisticaMaritima =  logisticaMaritimaRepository.findById(id);
        if (logisticaMaritima.isEmpty()) throw new NotFoundException("Dato no encontrado");
        logisticaMaritimaRepository.deleteById(id);
    }
}
