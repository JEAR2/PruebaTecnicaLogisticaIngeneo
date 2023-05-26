package com.reto.pruebatecnicalogistica.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "logisticaTerrestre")
public class LogisticaTerrestre extends Logistica{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Pattern(regexp = "[A-Za-z]{3}\\d{3}", message = "El formato de la placa debe ser de 3 letras iniciales seguidas de 3 números")
    private String placaVehiculo;
    @Pattern(regexp = "[a-zA-Z0-9]{10}", message = "El número de guía debe ser alfanumérico y tener 10 dígitos")
    private String numeroGuia;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private Bodega bodega;
    @ManyToOne
    @JoinColumn(name = "tipo_producto_id")
    private TipoProducto tipoProducto;
    @Override
    String getNumeroGuia() {
        return null;
    }
}
