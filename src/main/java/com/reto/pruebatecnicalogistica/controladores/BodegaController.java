package com.reto.pruebatecnicalogistica.controladores;


import com.reto.pruebatecnicalogistica.entidades.Bodega;
import com.reto.pruebatecnicalogistica.servicios.implementacion.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logistica/bodega")
public class BodegaController {
    @Autowired
    private BodegaService bodegaService;

    @GetMapping(value = "/todas")
    public List<Bodega> ObtenerBodegas(){
        return bodegaService.ObtenerBodegas();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/guardar")
    public ResponseEntity<Bodega> guardarBodega(@RequestBody Bodega bodega){

        bodega.setTelefono(Long.valueOf(bodega.getTelefono()));
        Bodega bodegaGuardar =  bodegaService.CrearBodega(bodega);
        return new ResponseEntity<>(bodegaGuardar, HttpStatus.OK);
    }



    @GetMapping(path = "/obtener/{id}")
    public Optional<Bodega> encontrarBodegaPorId(@PathVariable("id") Long id){

        return bodegaService.ObtenerBodegaPorId(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/actualizar/{id}")
    public ResponseEntity<Bodega> actualizarBodega(@RequestBody Bodega bodega,@PathVariable("id") Long id ){

        return new ResponseEntity<Bodega>(bodegaService.ActualizarBodega(id,bodega), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void> eliminarBodega(@PathVariable Long id){
        Optional bodega = null;
        bodega = bodegaService.ObtenerBodegaPorId(id);
        if(bodega.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        bodegaService.EliminarBodega(id);
        return ResponseEntity.ok().build();
    }



}
