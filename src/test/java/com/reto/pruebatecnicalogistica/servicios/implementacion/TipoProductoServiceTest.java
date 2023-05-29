package com.reto.pruebatecnicalogistica.servicios.implementacion;

import com.reto.pruebatecnicalogistica.entidades.TipoProducto;
import com.reto.pruebatecnicalogistica.repositorios.TipoProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipoProductoServiceTest {
    @Mock
    private TipoProductoRepository tipoProductoRepository;
    @InjectMocks
    private TipoProductoService tipoProductoService;

    private TipoProducto tipoProducto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        tipoProducto = new TipoProducto();
        tipoProducto.setId(1L);
        tipoProducto.setNombre("bodega");
        tipoProducto.setDescripcion("descripcion");
    }

    @Test
    void obtenerTiposProducto() {
        when(tipoProductoRepository.findAll()).thenReturn(Arrays.asList(tipoProducto));
        assertNotNull(tipoProductoService.ObtenerTiposProducto());
    }

    @Test
    void obtenerTipoProductoPorId() {
        when(tipoProductoRepository.findById(tipoProducto.getId())).thenReturn(Optional.ofNullable(tipoProducto));
        assertNotNull(tipoProductoService.ObtenerTipoProductoPorId(tipoProducto.getId()));
    }

    @Test
    void crearTipoProducto() {
        when(tipoProductoRepository.save(tipoProducto)).thenReturn(tipoProducto);
        assertNotNull(tipoProductoService.CrearTipoProducto(tipoProducto));
        verify(tipoProductoRepository,times(1)).save(tipoProducto);
    }

    @Test
    void actualizarTipoProducto() {
        when(tipoProductoRepository.findById(tipoProducto.getId())).thenReturn(Optional.ofNullable(tipoProducto));
        when(tipoProductoRepository.save(tipoProducto)).thenReturn(tipoProducto);
        assertNotNull(tipoProductoService.ActualizarTipoProducto(1L,tipoProducto));
        verify(tipoProductoRepository,times(1)).save(tipoProducto);
    }

    @Test
    void eliminarTipoProducto() {
        tipoProductoRepository.deleteById(tipoProducto.getId());
        verify(tipoProductoRepository,times(1)).deleteById(tipoProducto.getId());
    }
}