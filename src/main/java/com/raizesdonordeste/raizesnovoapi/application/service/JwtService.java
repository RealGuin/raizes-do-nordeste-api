package com.raizesdonordeste.raizesnovoapi.application.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.domain.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    private static final String CHAVE_SECRETA = "minha-chave-secreta-do-jwt-deve-ter-pelo-menos-32-bytes";
    private static final long EXPIRACAO = 1000 * 60 * 60;

    public String gerarToken(Usuario usuario) {
        SecretKey chave = Keys.hmacShaKeyFor(CHAVE_SECRETA.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("role", usuario.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRACAO))
                .signWith(chave)
                .compact();
    }
}
