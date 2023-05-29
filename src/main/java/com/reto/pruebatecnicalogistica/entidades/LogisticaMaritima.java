package com.reto.pruebatecnicalogistica.entidades;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

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
    private int cantidadProducto;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaRegistro;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaEntrega;
    private Double precioEnvio;
    private Double descuento = 0.0;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "puerto_id")
    private Puerto puerto;
    @ManyToOne
    @JoinColumn(name = "tipo_producto_id")
    private TipoProducto tipoProducto;


}
