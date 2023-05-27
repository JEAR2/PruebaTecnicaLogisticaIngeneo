package com.reto.pruebatecnicalogistica.controladores;



import com.reto.pruebatecnicalogistica.entidades.LogisticaMaritima;
import com.reto.pruebatecnicalogistica.servicios.implementacion.LogisticaMaritimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logistica/maritima")
public class LogisticaMaritimaController {
    @Autowired
    private LogisticaMaritimaService logisticaMaritimaService;

    @GetMapping(value = "/todos")
    public List<LogisticaMaritima> ObtenerLogisticaMaritima(){
        return logisticaMaritimaService.ObtenerLogisticaMaritima();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/guardar")
    public ResponseEntity<LogisticaMaritima> guardarLogisticaMaritima(@RequestBody LogisticaMaritima logisticaMaritima){
        if(logisticaMaritima.getCantidadProducto()>10){
            logisticaMaritima.setDescuento(0.03);
        }
        LogisticaMaritima logisticaMaritimaGuardar =  logisticaMaritimaService.CrearLogisticaMaritima(logisticaMaritima);
        return new ResponseEntity<>(logisticaMaritimaGuardar, HttpStatus.OK);
    }



    @GetMapping(path = "/obtener/{id}")
    public Optional<LogisticaMaritima> encontrarLogisticaMaritimaPorId(@PathVariable("id") Long id){

        return logisticaMaritimaService.ObtenerLogisticaMaritimaPorId(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/actualizar/{id}")
    public ResponseEntity<LogisticaMaritima> actualizarLogisticaMaritima(@RequestBody LogisticaMaritima logisticaMaritima,@PathVariable("id") Long id ){

        return new ResponseEntity<LogisticaMaritima>(logisticaMaritimaService.ActualizarLogisticaMaritima(id,logisticaMaritima), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void> eliminarLogisticaCamiones(@PathVariable Long id){
        Optional logisticaMaritima;
        logisticaMaritima = logisticaMaritimaService.ObtenerLogisticaMaritimaPorId(id);
        if (logisticaMaritima.isPresent()) {
            logisticaMaritimaService.EliminarLogisticaMaritima(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
