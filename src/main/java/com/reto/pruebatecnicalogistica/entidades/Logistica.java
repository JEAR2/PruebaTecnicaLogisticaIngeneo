package com.reto.pruebatecnicalogistica.entidades;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
public class Logistica {
    private Long id;
    private int cantidadProducto;
    private Date fechaRegistro;
    private Date fechaEntrega;
    private Double precioEnvio;
    private Double descuento = 0.0;

    public Logistica() {
    }


}
