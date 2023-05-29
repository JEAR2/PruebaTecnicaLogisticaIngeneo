package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.entidades.Bodega;
import com.reto.pruebatecnicalogistica.repositorios.BodegaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BodegaServiceTest {

    @Mock
    private BodegaRepository bodegaRepository;
    @InjectMocks
    private BodegaService bodegaService;

    private Bodega bodega;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        bodega = new Bodega();
        bodega.setId(1L);
        bodega.setNombre("bodega");
        bodega.setTelefono(1233654L);
        bodega.setUbicacion("pamplona");
    }
    @Test
    void obtenerBodegas() {
        when(bodegaRepository.findAll()).thenReturn(Arrays.asList(bodega));
        assertNotNull(bodegaService.ObtenerBodegas());
    }

    @Test
    void obtenerBodegaPorId() {
        when(bodegaRepository.findById(bodega.getId())).thenReturn(Optional.ofNullable(bodega));
        assertNotNull(bodegaService.ObtenerBodegaPorId(bodega.getId()));
    }

    @Test
    void crearBodega() {
        when(bodegaRepository.save(bodega)).thenReturn(bodega);
        assertNotNull(bodegaService.CrearBodega(bodega));
        verify(bodegaRepository,times(1)).save(bodega);
    }

    @Test
    void actualizarBodega() {
        when(bodegaRepository.findById(bodega.getId())).thenReturn(Optional.ofNullable(bodega));
        when(bodegaRepository.save(bodega)).thenReturn(bodega);
        assertNotNull(bodegaService.ActualizarBodega(1L,bodega));
        verify(bodegaRepository,times(1)).save(bodega);
    }

    @Test
    void eliminarBodega() {
        bodegaRepository.deleteById(bodega.getId());
        verify(bodegaRepository,times(1)).deleteById(bodega.getId());
    }
}