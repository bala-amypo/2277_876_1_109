package com.example.demo.entity;

import java.time.LocalDateTime;

public class UserProfile {
    private Long id;
    private String userId;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private Boolean active;
    private LocalDateTime createdAt;

    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.role == null) this.role = "USER";
    }

    // getters & setters
    // (generate all – tests use them directly)
}
