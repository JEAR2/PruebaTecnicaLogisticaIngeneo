package com.reto.pruebatecnicalogistica.utils;

import com.reto.pruebatecnicalogistica.security.entidad.Rol;
import com.reto.pruebatecnicalogistica.security.enums.RolNombre;
import com.reto.pruebatecnicalogistica.security.servicio.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CrearRoles implements CommandLineRunner {
    @Autowired
    RolService rolService;
    @Override
    public void run(String... args) throws Exception {
//        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
//        Rol rolUser = new Rol(RolNombre.ROLE_USER);
//        rolService.save(rolAdmin);
//        rolService.save(rolUser);
    }
}
