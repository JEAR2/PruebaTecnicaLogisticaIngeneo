package com.reto.pruebatecnicalogistica.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {
    @Id
    private Long Id;
    @NotBlank
    private Long Identificacion;
    @NotBlank
    private String nombre;
    @NotBlank
    private Long telefono;
    @NotBlank
    private String sexo;
    @NotBlank
    private String Correo;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<LogisticaMaritima> logisticaMaritima;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<LogisticaTerrestre> logisticaTerrestre;
}
