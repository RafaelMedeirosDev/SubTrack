package com.example.SubTrack.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.SubTrack.shared.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

    @Value("${JWT_SECRET}")
    private String SECRET_KEY;

    @Value("${JWT_ISSUER}")
    private String ISSUER;

    public String generateToken(UserDetailsImpl user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getUser().getId().toString())
                    .withClaim("name", user.getUser().getName())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(7200))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token JWT.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    public String getNameFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getClaim("name").asString();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    public String getIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getClaim("id").asString();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    // @Bean
    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token.replace("Bearer ", ""));
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}