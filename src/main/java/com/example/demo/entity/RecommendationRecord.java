package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="recommendations")
public class RecommendationRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long purchaseIntentId;
    private Long recommendedCardId;
    private Double expectedRewardValue;
    private String calculationDetailsJson;
    private LocalDateTime recommendedAt;

    @PrePersist
    public void prePersist(){
        this.recommendedAt = LocalDateTime.now();
        if(this.expectedRewardValue==null) this.expectedRewardValue=0.0;
    }

    // getters & setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public Long getUserId(){ return userId; }
    public void setUserId(Long userId){ this.userId=userId; }

    public Long getPurchaseIntentId(){ return purchaseIntentId; }
    public void setPurchaseIntentId(Long pid){ this.purchaseIntentId=pid; }

    public Long getRecommendedCardId(){ return recommendedCardId; }
    public void setRecommendedCardId(Long id){ this.recommendedCardId=id; }

    public Double getExpectedRewardValue(){ return expectedRewardValue; }
    public void setExpectedRewardValue(Double v){ this.expectedRewardValue=v; }

    public String getCalculationDetailsJson(){ return calculationDetailsJson; }
    public void setCalculationDetailsJson(String j){ this.calculationDetailsJson=j; }

    public LocalDateTime getRecommendedAt(){ return recommendedAt; }
    public void setRecommendedAt(LocalDateTime t){ this.recommendedAt=t; }
}
