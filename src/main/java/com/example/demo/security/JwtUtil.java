package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

public class JwtUtil {

    private final byte[] secret;
    private final long expirationMs;

    // REQUIRED constructor for tests
    public JwtUtil(byte[] secret, Long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    // -------- Generate JWT --------
    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .claim("uid", userId)
                .claim("email", email)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(Keys.hmacShaKeyFor(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    // -------- Claims Parser --------
    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e) {
            return null;
        }
    }

    public String extractEmail(String token) {
        Claims c = getClaims(token);
        return c == null ? null : c.get("email", String.class);
    }

    public Long extractUserId(String token) {
        Claims c = getClaims(token);
        return c == null ? null : c.get("uid", Long.class);
    }

    public String extractRole(String token) {
        Claims c = getClaims(token);
        return c == null ? null : c.get("role", String.class);
    }

    public boolean validateToken(String token) {
        return getClaims(token) != null;
    }
}
