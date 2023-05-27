package com.reto.pruebatecnicalogistica.utils;

import lombok.Data;

@Data
public class Mensaje {
    private String mensaje;
    public Mensaje(String mensaje){ this.mensaje = mensaje;}

}
