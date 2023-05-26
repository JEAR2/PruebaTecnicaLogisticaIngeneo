package com.reto.pruebatecnicalogistica.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "logisticaMaritima")
public class LogisticaMaritima extends Logistica{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank()
    private String NumeroFlota;
    @Pattern(regexp = "[a-zA-Z0-9]{10}", message = "El número de guía debe ser alfanumérico y tener 10 dígitos")
    private String NumeroGuia;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "puerto_id")
    private Puerto puerto;
    @ManyToOne
    @JoinColumn(name = "tipo_producto_id")
    private TipoProducto tipoProducto;

    @Override
    String getNumeroGuia() {
        return null;
    }
}
