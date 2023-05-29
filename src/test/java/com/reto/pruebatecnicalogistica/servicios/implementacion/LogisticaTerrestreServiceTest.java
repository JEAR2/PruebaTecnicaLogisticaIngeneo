package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.entidades.Bodega;
import com.reto.pruebatecnicalogistica.entidades.Cliente;
import com.reto.pruebatecnicalogistica.entidades.LogisticaTerrestre;
import com.reto.pruebatecnicalogistica.entidades.TipoProducto;
import com.reto.pruebatecnicalogistica.repositorios.LogisticaTerrestreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogisticaTerrestreServiceTest {
    @Mock
    private LogisticaTerrestreRepository logisticaTerrestreRepository;
    @InjectMocks
    private LogisticaTerrestreService logisticaTerrestreService;

    private LogisticaTerrestre logisticaTerrestre;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        logisticaTerrestre = new LogisticaTerrestre();
        logisticaTerrestre.setId(1L);
        logisticaTerrestre.setPlacaVehiculo("bodega");
        logisticaTerrestre.setNumeroGuia("1233654L");
        logisticaTerrestre.setCantidadProducto(12);
        logisticaTerrestre.setFechaRegistro(new Date());
        logisticaTerrestre.setFechaEntrega(new Date());
        logisticaTerrestre.setPrecioEnvio(1.2345);
        logisticaTerrestre.setCliente(new Cliente());
        logisticaTerrestre.setBodega(new Bodega());
        logisticaTerrestre.setTipoProducto(new TipoProducto());
    }

    @Test
    void obtenerLogisticaTerrestre() {
        when(logisticaTerrestreRepository.findAll()).thenReturn(Arrays.asList(logisticaTerrestre));
        assertNotNull(logisticaTerrestreService.ObtenerLogisticaTerrestre());
    }

    @Test
    void obtenerLogisticaTerrestrePorId() {
        when(logisticaTerrestreRepository.findById(logisticaTerrestre.getId())).thenReturn(Optional.ofNullable(logisticaTerrestre));
        assertNotNull(logisticaTerrestreService.ObtenerLogisticaTerrestrePorId(logisticaTerrestre.getId()));
    }

    @Test
    void crearLogisticaTerrestre() {
        when(logisticaTerrestreRepository.save(logisticaTerrestre)).thenReturn(logisticaTerrestre);
        assertNotNull(logisticaTerrestreService.CrearLogisticaTerrestre(logisticaTerrestre));
        verify(logisticaTerrestreRepository,times(1)).save(logisticaTerrestre);
    }

    @Test
    void actualizarLogisticaTerrestre() {
        when(logisticaTerrestreRepository.findById(logisticaTerrestre.getId())).thenReturn(Optional.ofNullable(logisticaTerrestre));
        when(logisticaTerrestreRepository.save(logisticaTerrestre)).thenReturn(logisticaTerrestre);
        assertNotNull(logisticaTerrestreService.ActualizarLogisticaTerrestre(1L,logisticaTerrestre));
        verify(logisticaTerrestreRepository,times(1)).save(logisticaTerrestre);
    }

    @Test
    void eliminarLogisticaTerrestre() {
        logisticaTerrestreRepository.deleteById(logisticaTerrestre.getId());
        verify(logisticaTerrestreRepository,times(1)).deleteById(logisticaTerrestre.getId());
    }
}