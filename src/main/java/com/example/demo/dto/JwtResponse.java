package com.example.demo.dto;

public class JwtResponse {

    private Long userId;
    private String email;
    private String token;
    private String role;

    // Constructor with all 4 fields
    public JwtResponse(Long userId, String email, String token, String role) {
        this.userId = userId;
        this.email = email;
        this.token = token;
        this.role = role;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
