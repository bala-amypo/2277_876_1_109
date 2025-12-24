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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getCardName() { return cardName; }
    public void setCardName(String cardName) { this.cardName = cardName; }

    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getAnnualFee() { return annualFee; }
    public void setAnnualFee(Double annualFee) { this.annualFee = annualFee; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
