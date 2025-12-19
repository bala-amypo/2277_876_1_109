package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "reward_rule",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "cardId", "category" })
    }
)
public class RewardRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associated credit card
    @Column(nullable = false)
    private Long cardId;

    // Spend category: GROCERIES, TRAVEL, etc.
    @Column(nullable = false)
    private String category;

    // POINTS, MILES, CASHBACK
    @Column(nullable = false)
    private String rewardType;

    // Reward multiplier
    @Column(nullable = false)
    private Double multiplier;

    // Active or inactive rule
    @Column(nullable = false)
    private Boolean active;

    // Constructors
    public RewardRule() {
    }

    public RewardRule(Long cardId, String category, String rewardType,
                      Double multiplier, Boolean active) {
        this.cardId = cardId;
        this.category = category;
        this.rewardType = rewardType;
        this.multiplier = multiplier;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
