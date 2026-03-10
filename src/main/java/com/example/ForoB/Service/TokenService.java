package com.example.ForoB.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String apiSecret;

    @Value("${jwt.expiration}")
    private Integer expirationHours;

    public String generarToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foroB_API")
                    .withSubject(username)
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(expirationHours).toInstant(ZoneOffset.of("-05:00"));
    }


    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("El token es nulo.");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            return JWT.require(algorithm)
                    .withIssuer("foroB_API")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (com.auth0.jwt.exceptions.JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido o expirado.", exception);
        }
    }
}