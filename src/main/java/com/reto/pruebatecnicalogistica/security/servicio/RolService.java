package com.reto.pruebatecnicalogistica.security.servicio;

import com.reto.pruebatecnicalogistica.security.entidad.Rol;
import com.reto.pruebatecnicalogistica.security.enums.RolNombre;
import com.reto.pruebatecnicalogistica.security.repositorio.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
