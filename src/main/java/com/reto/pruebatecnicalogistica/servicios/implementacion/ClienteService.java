package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.config.exceptions.BadRequestException;
import com.reto.pruebatecnicalogistica.config.exceptions.NotFoundException;
import com.reto.pruebatecnicalogistica.entidades.Cliente;
import com.reto.pruebatecnicalogistica.repositorios.ClienteRepository;
import com.reto.pruebatecnicalogistica.servicios.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;



    @Override
    public List<Cliente> ObtenerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> ObtenerClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isEmpty()) throw new NotFoundException("Cliente no encontrado");
        return cliente;
    }

    @Override
    public Cliente CrearCliente(Cliente cliente) {
        if(cliente.getIdentificacion()==null || cliente.getCorreo() == null){
            throw new BadRequestException("Datos de cliente incompletos (Obligatorio: Identificación y Correo)");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente ActualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteActualizar = clienteRepository.findById(id);
        if(clienteActualizar.isEmpty()) throw new NotFoundException("Cliente no encontrado");
        return clienteRepository.save(cliente);
    }

    @Override
    public void EliminarCliente(Long id) {
        Optional<Cliente> clienteActualizar = clienteRepository.findById(id);
        if(clienteActualizar.isEmpty()) throw new NotFoundException("Cliente no encontrado");
        clienteRepository.deleteById(id);
    }
}
