package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;

import java.util.List;

public class RecommendationEngineServiceImpl implements RecommendationEngineService {

    private final PurchaseIntentRecordRepository intentRepository;
    private final UserProfileRepository userRepository;
    private final CreditCardRecordRepository cardRepository;
    private final RewardRuleRepository ruleRepository;
    private final RecommendationRecordRepository recommendationRepository;

    public RecommendationEngineServiceImpl(
            PurchaseIntentRecordRepository intentRepository,
            UserProfileRepository userRepository,
            CreditCardRecordRepository cardRepository,
            RewardRuleRepository ruleRepository,
            RecommendationRecordRepository recommendationRepository
    ) {
        this.intentRepository = intentRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.ruleRepository = ruleRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public RecommendationRecord generateRecommendation(Long purchaseIntentId) {
        PurchaseIntentRecord intent = intentRepository.findById(purchaseIntentId)
                .orElseThrow(() -> new BadRequestException("Intent not found"));

        UserProfile user = userRepository.findById(intent.getUserId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        List<CreditCardRecord> activeCards = cardRepository.findByUserIdAndActiveTrue(user.getId());
        if (activeCards.isEmpty()) throw new BadRequestException("No active cards");

        CreditCardRecord bestCard = activeCards.get(0);
        double maxReward = 0;

        for (CreditCardRecord card : activeCards) {
            List<RewardRule> rules = ruleRepository.findActiveRulesForCardCategory(card.getId(), intent.getCategory());
            for (RewardRule rule : rules) {
                double reward = intent.getAmount() * rule.getMultiplier();
                if (reward > maxReward) {
                    maxReward = reward;
                    bestCard = card;
                }
            }
        }

        RecommendationRecord rec = new RecommendationRecord();
        rec.setUserId(user.getId());
        rec.setPurchaseIntentId(intent.getId());
        rec.setRecommendedCardId(bestCard.getId());
        rec.setExpectedRewardValue(maxReward);

        return recommendationRepository.save(rec);
    }

    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }

    @Override
    public List<RecommendationRecord> getAllRecommendations() {
        return recommendationRepository.findAll();
    }
}
