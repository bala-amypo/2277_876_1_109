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
