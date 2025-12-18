package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "credit_card_records")
public class CreditCardRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String cardName;

    @Column(nullable = false)
    private String issuer;

    @Column(nullable = false)
    private String cardType;

    @Column(nullable = false)
    private Double annualFee;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Many-to-Many with UserProfile (favourited by users)
    @ManyToMany(mappedBy = "favouriteCards")
    private Set<UserProfile> favouredByUsers = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public CreditCardRecord() {
    }

    public CreditCardRecord(Long userId, String cardName, String issuer,
                            String cardType, Double annualFee, String status) {
        this.userId = userId;
        this.cardName = cardName;
        this.issuer = issuer;
        this.cardType = cardType;
        this.annualFee = annualFee;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getCardName() {
        return cardName;
    }
 
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
 
    public String getIssuer() {
        return issuer;
    }
 
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
 
    public String getCardType() {
        return cardType;
    }
 
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
 
    public Double getAnnualFee() {
        return annualFee;
    }
 
    public void setAnnualFee(Double annualFee) {
        if (annualFee < 0) {
            throw new IllegalArgumentException("Annual fee must be >= 0");
        }
        this.annualFee = annualFee;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<UserProfile> getFavouredByUsers() {
        return favouredByUsers;
    }

    public void setFavouredByUsers(Set<UserProfile> favouredByUsers) {
        this.favouredByUsers = favouredByUsers;
    }
}
