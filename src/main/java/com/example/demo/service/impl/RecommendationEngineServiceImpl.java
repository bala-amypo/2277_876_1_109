package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RecommendationEngineServiceImpl implements RecommendationEngineService {

    private final PurchaseIntentRecordRepository purchaseIntentRepository;
    private final UserProfileRepository userProfileRepository;
    private final CreditCardRecordRepository creditCardRepository;
    private final RewardRuleRepository rewardRuleRepository;
    private final RecommendationRecordRepository recommendationRecordRepository;

    public RecommendationEngineServiceImpl(
            PurchaseIntentRecordRepository purchaseIntentRepository,
            UserProfileRepository userProfileRepository,
            CreditCardRecordRepository creditCardRepository,
            RewardRuleRepository rewardRuleRepository,
            RecommendationRecordRepository recommendationRecordRepository
    ) {
        this.purchaseIntentRepository = purchaseIntentRepository;
        this.userProfileRepository = userProfileRepository;
        this.creditCardRepository = creditCardRepository;
        this.rewardRuleRepository = rewardRuleRepository;
        this.recommendationRecordRepository = recommendationRecordRepository;
    }

    @Override
    public RecommendationRecord generateRecommendation(Long intentId) {
        PurchaseIntentRecord intent = purchaseIntentRepository.findById(intentId)
                .orElseThrow(() -> new BadRequestException("Intent not found"));

        UserProfile user = userProfileRepository.findById(intent.getUserId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        List<CreditCardRecord> cards = creditCardRepository.findByUserIdAndActiveTrue(user.getId());
        if (cards.isEmpty()) {
            throw new BadRequestException("User has no active cards");
        }

        CreditCardRecord bestCard = cards.stream()
                .max(Comparator.comparingDouble(card -> calculateReward(card, intent)))
                .orElse(cards.get(0));

        RecommendationRecord recommendation = new RecommendationRecord();
        recommendation.setUserId(user.getId());
        recommendation.setPurchaseIntentId(intent.getId());
        recommendation.setRecommendedCardId(bestCard.getId());
        recommendation.setExpectedRewardValue(calculateReward(bestCard, intent));
        recommendation.setCalculationDetailsJson("{}");

        return recommendationRecordRepository.save(recommendation);
    }

    private double calculateReward(CreditCardRecord card, PurchaseIntentRecord intent) {
        return rewardRuleRepository.findByCardIdAndCategory(card.getId(), intent.getCategory())
                .stream()
                .filter(RewardRule::getActive)
                .mapToDouble(rule -> intent.getAmount() * rule.getMultiplier())
                .sum();
    }

    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        return recommendationRecordRepository.findByUserId(userId);
    }

    @Override
    public List<RecommendationRecord> getAllRecommendations() {
        return recommendationRecordRepository.findAll();
    }
}
