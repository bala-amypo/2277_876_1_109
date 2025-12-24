package com.example.demo.dto;

public class JwtResponse {
    private Long userId;
    private String email;
    private String role;
    private String token;

    public JwtResponse(Long userId, String email, String role, String token) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getToken() { return token; }
}
