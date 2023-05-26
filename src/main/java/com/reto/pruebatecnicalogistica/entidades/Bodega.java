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
@Table(name = "bodega")
public class Bodega {
    @Id
    private Long Id;
    @NotBlank
    private String nombre;
    @NotBlank
    private Long telefono;
    @NotBlank
    private String ubicacion;
    @OneToMany(mappedBy = "bodega", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<LogisticaTerrestre> logisticaTerrestre;
}
