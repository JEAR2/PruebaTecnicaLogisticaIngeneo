package com.reto.pruebatecnicalogistica.servicios.interfaces;



import com.reto.pruebatecnicalogistica.entidades.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> ObtenerClientes();
    Optional<Cliente> ObtenerClientePorId(Long id);
    Cliente CrearCliente(Cliente cliente);
    Cliente ActualizarCliente(Long id, Cliente cliente) ;
    void EliminarCliente(Long id);
}
