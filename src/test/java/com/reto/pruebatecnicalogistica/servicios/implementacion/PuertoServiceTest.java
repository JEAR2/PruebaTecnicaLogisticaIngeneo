package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.entidades.Puerto;
import com.reto.pruebatecnicalogistica.repositorios.PuertoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PuertoServiceTest {
    @Mock
    private PuertoRepository puertoRepository;
    @InjectMocks
    private PuertoService puertoService;

    private Puerto puerto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        puerto = new Puerto();
        puerto.setId(1L);
        puerto.setNombre("puerto");
        puerto.setTelefono(1233654L);
        puerto.setUbicacion("pamplona");
    }
    @Test
    void obtenerPuertos() {
        when(puertoRepository.findAll()).thenReturn(Arrays.asList(puerto));
        assertNotNull(puertoService.ObtenerPuertos());
    }

    @Test
    void obtenerPuertoPorId() {
        when(puertoRepository.findById(puerto.getId())).thenReturn(Optional.ofNullable(puerto));
        assertNotNull(puertoService.ObtenerPuertoPorId(puerto.getId()));
    }

    @Test
    void crearPuerto() {
        when(puertoRepository.save(puerto)).thenReturn(puerto);
        assertNotNull(puertoService.CrearPuerto(puerto));
        verify(puertoRepository,times(1)).save(puerto);
    }

    @Test
    void actualizarPuerto() {
        when(puertoRepository.findById(puerto.getId())).thenReturn(Optional.ofNullable(puerto));
        when(puertoRepository.save(puerto)).thenReturn(puerto);
        assertNotNull(puertoService.ActualizarPuerto(1L,puerto));
        verify(puertoRepository,times(1)).save(puerto);
    }

    @Test
    void eliminarPuerto() {
        puertoRepository.deleteById(puerto.getId());
        verify(puertoRepository,times(1)).deleteById(puerto.getId());
    }
}