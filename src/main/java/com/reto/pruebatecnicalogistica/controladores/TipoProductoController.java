package com.reto.pruebatecnicalogistica.controladores;


import com.reto.pruebatecnicalogistica.entidades.TipoProducto;
import com.reto.pruebatecnicalogistica.servicios.implementacion.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logistica/tipo-producto")
public class TipoProductoController {
    @Autowired
    private TipoProductoService tipoProductoService;

    @GetMapping(value = "/todos")
    public List<TipoProducto> ObtenerTiposProducto(){
        return tipoProductoService.ObtenerTiposProducto();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/guardar")
    public ResponseEntity<TipoProducto> guardarTipoProducto(@RequestBody TipoProducto tipoProducto){
        TipoProducto tipoProductoGuardar =  tipoProductoService.CrearTipoProducto(tipoProducto);
        return new ResponseEntity<>(tipoProductoGuardar, HttpStatus.OK);
    }



    @GetMapping(path = "/obtener/{id}")
    public Optional<TipoProducto> encontrarTipoProductoPorId(@PathVariable("id") Long id){

        return tipoProductoService.ObtenerTipoProductoPorId(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/actualizar/{id}")
    public ResponseEntity<TipoProducto> actualizarTipoProducto(@RequestBody TipoProducto tipoProducto,@PathVariable("id") Long id ){

        return new ResponseEntity<TipoProducto>(tipoProductoService.ActualizarTipoProducto(id,tipoProducto), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void> eliminarTipoProducto(@PathVariable Long id){
        Optional tipoProducto = null;
        tipoProducto = tipoProductoService.ObtenerTipoProductoPorId(id);
        if(tipoProducto.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        tipoProductoService.EliminarTipoProducto(id);
        return ResponseEntity.ok().build();
    }
}
