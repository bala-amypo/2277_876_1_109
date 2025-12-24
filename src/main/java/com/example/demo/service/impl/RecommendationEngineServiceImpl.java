package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service;

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
            RecommendationRecordRepository recommendationRecordRepository) {
        this.purchaseIntentRepository = purchaseIntentRepository;
        this.userProfileRepository = userProfileRepository;
        this.creditCardRepository = creditCardRepository;
        this.rewardRuleRepository = rewardRuleRepository;
        this.recommendationRecordRepository = recommendationRecordRepository;
    }

    @Override
    public RecommendationRecord generateRecommendation(Long purchaseIntentId) {
        PurchaseIntentRecord intent = purchaseIntentRepository.findById(purchaseIntentId)
                .orElseThrow(() -> new BadRequestException("PurchaseIntent not found"));

        UserProfile user = userProfileRepository.findById(intent.getUserId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        List<CreditCardRecord> cards = creditCardRepository.findActiveCardsByUser(user.getId());
        if (cards.isEmpty()) throw new BadRequestException("No active cards for user");

        CreditCardRecord bestCard = cards.get(0);
        double maxReward = 0;

        for (CreditCardRecord card : cards) {
            List<RewardRule> rules = rewardRuleRepository.findActiveRulesForCardCategory(card.getId(), intent.getCategory());
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
        rec.setPurchaseIntentId(purchaseIntentId);
        rec.setRecommendedCardId(bestCard.getId());
        rec.setExpectedRewardValue(maxReward);
        rec.setCalculationDetailsJson("{\"dummy\":\"calc\"}");

        return recommendationRecordRepository.save(rec);
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
