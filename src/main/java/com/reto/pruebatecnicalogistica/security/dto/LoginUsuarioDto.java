package com.reto.pruebatecnicalogistica.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class LoginUsuarioDto {
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;
}
