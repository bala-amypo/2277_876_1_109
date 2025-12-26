package com.example.demo.security;

import java.util.*;
import java.util.Base64;

public class JwtUtil {

    public JwtUtil(byte[] secret, long exp) {}

    public String generateToken(Long id, String email, String role) {
        return Base64.getEncoder()
            .encodeToString((id + "|" + email + "|" + role).getBytes());
    }

    public boolean validateToken(String token) {
        try {
            Base64.getDecoder().decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtUtil {

    private final byte[] secret;
    private final long expiry;

    public JwtUtil(byte[] secret, Long expiryMs){
        this.secret = secret;
        this.expiry = expiryMs;
    }

    public String generateToken(Long userId, String email, String role){
        return Jwts.builder()
                .claim("uid", userId)
                .claim("email", email)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis()+expiry))
                .signWith(Keys.hmacShaKeyFor(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims claims(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex){
            return null;
        }
    }

    public String extractEmail(String token){
        Claims c = claims(token);
        return c==null ? null : c.get("email",String.class);
    }

    public String extractRole(String token){
        Claims c = claims(token);
        return c==null ? null : c.get("role",String.class);
    }

    public Long extractUserId(String token){
        Claims c = claims(token);
        return c==null ? null : c.get("uid",Long.class);
    }

    public boolean validateToken(String token){
        return claims(token)!=null;
    }
}


    public Long extractUserId(String token) {
        return Long.valueOf(new String(Base64.getDecoder().decode(token)).split("\\|")[0]);
    }

    public String extractEmail(String token) {
        return new String(Base64.getDecoder().decode(token)).split("\\|")[1];
    }

    public String extractRole(String token) {
        return new String(Base64.getDecoder().decode(token)).split("\\|")[2];
    }
}
