package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final byte[] secret;
    private final long expirationMs;

    public JwtUtil(byte[] secret, long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("role", role)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String extractRole(String token) {
        return (String) Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().get("role");
    }

    public Long extractUserId(String token) {
        return ((Number) Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().get("userId")).longValue();
    }
}
