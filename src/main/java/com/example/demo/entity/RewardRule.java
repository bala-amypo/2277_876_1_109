package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "reward_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"card_id", "category"})
    }
)
public class RewardRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_id", nullable = false)
    private Long cardId;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String rewardType;

    @Column(nullable = false)
    private double multiplier;

    @Column(nullable = false)
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public RewardRule() {
    }

    public RewardRule(Long cardId, String category, String rewardType,
                      double multiplier, boolean active) {
        this.cardId = cardId;
        this.category = category;
        this.rewardType = rewardType;
        this.multiplier = multiplier;
        this.active = active;
    }
}
