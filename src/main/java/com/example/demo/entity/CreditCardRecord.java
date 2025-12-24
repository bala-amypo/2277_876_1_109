package com.example.demo.entity;

import java.time.LocalDateTime;

public class CreditCardRecord {
    private Long id;
    private Long userId;
    private String cardName;
    private String issuer;
    private String status;
    private Double annualFee;
    private LocalDateTime createdAt;

    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
}
