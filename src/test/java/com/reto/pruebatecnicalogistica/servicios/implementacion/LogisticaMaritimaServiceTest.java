package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.entidades.Cliente;
import com.reto.pruebatecnicalogistica.entidades.LogisticaMaritima;
import com.reto.pruebatecnicalogistica.entidades.Puerto;
import com.reto.pruebatecnicalogistica.entidades.TipoProducto;
import com.reto.pruebatecnicalogistica.repositorios.LogisticaMaritimaRepository;
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

class LogisticaMaritimaServiceTest {
    @Mock
    private LogisticaMaritimaRepository logisticaMaritimaRepository;
    @InjectMocks
    private LogisticaMaritimaService logisticaMaritimaService;

    private LogisticaMaritima logisticaMaritima;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        logisticaMaritima = new LogisticaMaritima();
        logisticaMaritima.setId(1L);
        logisticaMaritima.setNumeroFlota("12345");
        logisticaMaritima.setNumeroGuia("1233654L");
        logisticaMaritima.setCantidadProducto(12);
        logisticaMaritima.setFechaRegistro(new Date());
        logisticaMaritima.setFechaEntrega(new Date());
        logisticaMaritima.setPrecioEnvio(1.2345);
        logisticaMaritima.setCliente(new Cliente());
        logisticaMaritima.setPuerto(new Puerto());
        logisticaMaritima.setTipoProducto(new TipoProducto());
    }

    @Test
    void obtenerLogisticaMaritima() {
        when(logisticaMaritimaRepository.findAll()).thenReturn(Arrays.asList(logisticaMaritima));
        assertNotNull(logisticaMaritimaService.ObtenerLogisticaMaritima());
    }

    @Test
    void obtenerLogisticaMaritimaPorId() {
        when(logisticaMaritimaRepository.findById(logisticaMaritima.getId())).thenReturn(Optional.ofNullable(logisticaMaritima));
        assertNotNull(logisticaMaritimaService.ObtenerLogisticaMaritimaPorId(logisticaMaritima.getId()));
    }

    @Test
    void crearLogisticaMaritima() {
        when(logisticaMaritimaRepository.save(logisticaMaritima)).thenReturn(logisticaMaritima);
        assertNotNull(logisticaMaritimaService.CrearLogisticaMaritima(logisticaMaritima));
        verify(logisticaMaritimaRepository,times(1)).save(logisticaMaritima);
    }

    @Test
    void actualizarLogisticaMaritima() {
        when(logisticaMaritimaRepository.findById(logisticaMaritima.getId())).thenReturn(Optional.ofNullable(logisticaMaritima));
        when(logisticaMaritimaRepository.save(logisticaMaritima)).thenReturn(logisticaMaritima);
        assertNotNull(logisticaMaritimaService.ActualizarLogisticaMaritima(1L,logisticaMaritima));
        verify(logisticaMaritimaRepository,times(1)).save(logisticaMaritima);
    }

    @Test
    void eliminarLogisticaMaritima() {
        logisticaMaritimaRepository.deleteById(logisticaMaritima.getId());
        verify(logisticaMaritimaRepository,times(1)).deleteById(logisticaMaritima.getId());
    }
}