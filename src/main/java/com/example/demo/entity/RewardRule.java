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
    private Double multiplier;

    @Column(nullable = false)
    private Boolean active = true;

    public RewardRule() {
    }

    public RewardRule(Long cardId, String category, String rewardType,
                      Double multiplier, Boolean active) {
        this.cardId = cardId;
        this.category = category;
        this.rewardType = rewardType;
        setMultiplier(multiplier);   
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
        if (multiplier == null || multiplier <= 0) {
            throw new BadRequestException("Multiplier must be greater than 0");
        }
        this.multiplier = multiplier;
    }
 
    public Boolean getActive() {
        return active;
    }
 
    public void setActive(Boolean active) {
        this.active = active;
    }
}
