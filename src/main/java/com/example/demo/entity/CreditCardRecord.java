package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "credit_card_records")
public class CreditCardRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use "id" so setters/getters are standard

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = true)
    private String cardHolderName;

    // Default constructor
    public CreditCardRecord() {}

    // Parameterized constructor
    public CreditCardRecord(String cardNumber, Long userId, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.userId = userId;
        this.cardHolderName = cardHolderName;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}
