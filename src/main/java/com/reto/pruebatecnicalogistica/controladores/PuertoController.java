package com.reto.pruebatecnicalogistica.controladores;



import com.reto.pruebatecnicalogistica.entidades.Puerto;
import com.reto.pruebatecnicalogistica.servicios.implementacion.PuertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logistica/puerto")
public class PuertoController {
    @Autowired
    private PuertoService puertoService;

    @GetMapping(value = "/todos")
    public List<Puerto> ObtenerPuertos(){
        return puertoService.ObtenerPuertos();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/guardar")
    public ResponseEntity<Puerto> guardarPuerto(@RequestBody Puerto puerto){
        Puerto puertoGuardar =  puertoService.CrearPuerto(puerto);
        return new ResponseEntity<>(puertoGuardar, HttpStatus.OK);
    }



    @GetMapping(path = "/obtener/{id}")
    public Optional<Puerto> encontrarPuertoPorId(@PathVariable("id") Long id){

        return puertoService.ObtenerPuertoPorId(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/actualizar/{id}")
    public ResponseEntity<Puerto> actualizarPuerto(@RequestBody Puerto puerto,@PathVariable("id") Long id ){

        return new ResponseEntity<Puerto>(puertoService.ActualizarPuerto(id,puerto), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void> eliminarPuerto(@PathVariable Long id){
        Optional puerto = null;
        puerto = puertoService.ObtenerPuertoPorId(id);
        if(puerto.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        puertoService.EliminarPuerto(id);
        return ResponseEntity.ok().build();
    }
}
