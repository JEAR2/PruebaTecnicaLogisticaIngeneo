package com.reto.pruebatecnicalogistica.security.controlador;

import com.reto.pruebatecnicalogistica.security.dto.JwtDto;
import com.reto.pruebatecnicalogistica.security.dto.LoginUsuarioDto;
import com.reto.pruebatecnicalogistica.security.dto.NuevoUsuarioDto;
import com.reto.pruebatecnicalogistica.security.entidad.Rol;
import com.reto.pruebatecnicalogistica.security.entidad.Usuario;
import com.reto.pruebatecnicalogistica.security.enums.RolNombre;
import com.reto.pruebatecnicalogistica.security.jwt.JwtProvider;
import com.reto.pruebatecnicalogistica.security.servicio.RolService;
import com.reto.pruebatecnicalogistica.security.servicio.UsuarioService;
import com.reto.pruebatecnicalogistica.utils.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.SecondaryTable;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuarioDto nuevoUsuarioDto, BindingResult bindingResult){
        System.out.println("wrwerwer");
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new Mensaje("Campos incorrectos o email inválido"), HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existsByNombreUsuario(nuevoUsuarioDto.getNombreUsuario())){
            return new ResponseEntity<>(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existsByEmail(nuevoUsuarioDto.getEmail())){
            return new ResponseEntity<>(new Mensaje("El correo ya existe"), HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario(nuevoUsuarioDto.getNombre(),nuevoUsuarioDto.getNombreUsuario(),nuevoUsuarioDto.getEmail(),nuevoUsuarioDto.getPassword());

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuarioDto.getRoles().contains("admin")){
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }

        usuario.setRoles(roles);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioService.save(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario Guardado"),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuarioDto loginUsuarioDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Campos incorrectos"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginUsuarioDto.getNombreUsuario(),loginUsuarioDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String Jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(Jwt,userDetails.getUsername(),userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto,HttpStatus.OK);
    }
}
