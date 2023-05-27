package com.reto.pruebatecnicalogistica.security.jwt;

import com.reto.pruebatecnicalogistica.security.entidad.UsuarioPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
* Clase JwtProvider
* Esta clase genera el token y mediante el método de validación verifica que el toke
* no esté expirado, etc
 */
@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal =(UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(getKey(secret))
                .compact();
    }

    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(getKey(secret)).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try {
            //Jwts.parser().setSigningKey(getKey(secret)).parseClaimsJwt(token).getBody();
              Jwts.parser().setSigningKey(getKey(secret)).parseClaimsJws(token).getBody();
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado");
            throw new RuntimeException(e);
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
            throw new RuntimeException(e);
        } catch (MalformedJwtException e) {
            logger.error("Token formado de manera incorrecta");
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            logger.error("Fallo en la firma del token ");
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            logger.error("Token vacio");
            throw new RuntimeException(e);
        } catch (Exception e){
            logger.error("fail token");
        }
        return false;
    }
    private List<String> getRoles(UsuarioPrincipal usuarioPrincipal){
        return usuarioPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
    private Key getKey(String secret){
        byte [] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
