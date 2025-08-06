package com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.jwt;


import com.proteccion.gestor_tareas_colaborativo.domain.user.TokenResponse;
import com.proteccion.gestor_tareas_colaborativo.domain.user.gateway.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Getter

public class JwtProviderImpl implements JwtProvider {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public TokenResponse generateToken(String userName) {
        Date issuedAt = new Date();
        Date expiration = new Date(System.currentTimeMillis() + 3600_000);

        String token = Jwts.builder()
                //.setSubject(userName)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return TokenResponse.builder().token(token)
                .expiration(expiration)
                .createdAt(issuedAt)
                .type("Bearer")
                .build();
    }

    public String extractUserName(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}
