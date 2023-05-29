package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.entidades.Cliente;
import com.reto.pruebatecnicalogistica.repositorios.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setIdentificacion(1L);
        cliente.setNombre("cliente");
        cliente.setTelefono(1233654L);
        cliente.setSexo("M");
        cliente.setCorreo("cliente@cliente.com");
    }
    @Test
    void obtenerClientes() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        assertNotNull(clienteService.ObtenerClientes());
    }

    @Test
    void obtenerClientePorId() {
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.ofNullable(cliente));
        assertNotNull(clienteService.ObtenerClientePorId(cliente.getId()));
    }

    @Test
    void crearCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        assertNotNull(clienteService.CrearCliente(cliente));
        verify(clienteRepository,times(1)).save(cliente);
    }

    @Test
    void actualizarCliente() {
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.ofNullable(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        assertNotNull(clienteService.ActualizarCliente(1L,cliente));
        verify(clienteRepository,times(1)).save(cliente);
    }

    @Test
    void eliminarCliente() {
        clienteRepository.deleteById(cliente.getId());
        verify(clienteRepository,times(1)).deleteById(cliente.getId());
    }
}