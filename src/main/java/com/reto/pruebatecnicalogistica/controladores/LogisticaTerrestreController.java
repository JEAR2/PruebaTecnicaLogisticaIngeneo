package com.reto.pruebatecnicalogistica.controladores;


import com.reto.pruebatecnicalogistica.entidades.LogisticaTerrestre;
import com.reto.pruebatecnicalogistica.servicios.implementacion.LogisticaTerrestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logistica/terrestre")
public class LogisticaTerrestreController {
    @Autowired
    private LogisticaTerrestreService logisticaTerrestreService;

    @GetMapping(value = "/todos")
    public List<LogisticaTerrestre> ObtenerLogisticaTerrestre(){
        return logisticaTerrestreService.ObtenerLogisticaTerrestre();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/guardar")
    public ResponseEntity<LogisticaTerrestre> guardarLogisticaTerrestre(@RequestBody LogisticaTerrestre logisticaTerrestre){
        if(logisticaTerrestre.getCantidadProducto()>10){
            logisticaTerrestre.setDescuento(0.05);
        }
        LogisticaTerrestre logisticaTerrestreGuardar =  logisticaTerrestreService.CrearLogisticaTerrestre(logisticaTerrestre);

        return new ResponseEntity<>(logisticaTerrestre, HttpStatus.OK);
    }



    @GetMapping(path = "/obtener/{id}")
    public Optional<LogisticaTerrestre> encontrarLogisticaTerrestrePorId(@PathVariable("id") Long id){

        return logisticaTerrestreService.ObtenerLogisticaTerrestrePorId(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/actualizar/{id}")
    public ResponseEntity<LogisticaTerrestre> actualizarLogisticaTerrestre(@RequestBody LogisticaTerrestre logisticaCamiones,@PathVariable("id") Long id ){

        return new ResponseEntity<LogisticaTerrestre>(logisticaTerrestreService.ActualizarLogisticaTerrestre(id,logisticaCamiones), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void> eliminarLogisticaTerrestre(@PathVariable Long id){
        Optional logisticaCamiones = null;
        logisticaCamiones = logisticaTerrestreService.ObtenerLogisticaTerrestrePorId(id);
        if(logisticaCamiones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        logisticaTerrestreService.EliminarLogisticaTerrestre(id);
        return ResponseEntity.ok().build();
    }
}
