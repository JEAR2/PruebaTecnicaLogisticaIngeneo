package com.reto.pruebatecnicalogistica.controladores;



import com.reto.pruebatecnicalogistica.entidades.Cliente;
import com.reto.pruebatecnicalogistica.servicios.implementacion.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logistica/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/todos")
    public List<Cliente> ObtenerClientes(){
        return clienteService.ObtenerClientes();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/guardar")
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente){
        Cliente clienteGuardar =  clienteService.CrearCliente(cliente);
        return new ResponseEntity<>(clienteGuardar, HttpStatus.OK);
    }



    @GetMapping(path = "/obtener/{id}")
    public Optional<Cliente> encontrarClientePorId(@PathVariable("id") Long id){

        return clienteService.ObtenerClientePorId(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/actualizar/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente,@PathVariable("id") Long id ){

        return new ResponseEntity<Cliente>(clienteService.ActualizarCliente(id,cliente), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){
        Optional cliente = null;
        cliente = clienteService.ObtenerClientePorId(id);
        if(cliente.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        clienteService.EliminarCliente(id);
        return ResponseEntity.ok().build();
    }
}
