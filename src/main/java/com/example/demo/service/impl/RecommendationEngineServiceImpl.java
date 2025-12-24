package com.example.demo.service.impl;

import java.util.*;
import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;

public class RecommendationEngineServiceImpl implements RecommendationEngineService {

    private final PurchaseIntentRecordRepository intentRepo;
    private final UserProfileRepository userRepo;
    private final CreditCardRecordRepository cardRepo;
    private final RewardRuleRepository ruleRepo;
    private final RecommendationRecordRepository recRepo;

    public RecommendationEngineServiceImpl(
        PurchaseIntentRecordRepository i,
        UserProfileRepository u,
        CreditCardRecordRepository c,
        RewardRuleRepository r,
        RecommendationRecordRepository rr
    ) {
        intentRepo = i;
        userRepo = u;
        cardRepo = c;
        ruleRepo = r;
        recRepo = rr;
    }

    public RecommendationRecord generateRecommendation(Long intentId) {
        PurchaseIntentRecord intent = intentRepo.findById(intentId)
            .orElseThrow(() -> new BadRequestException("Intent"));

        UserProfile user = userRepo.findById(intent.getUserId())
            .orElseThrow(() -> new BadRequestException("User"));

        List<CreditCardRecord> cards = cardRepo.findActiveCardsByUser(user.getId());
        if (cards.isEmpty()) throw new BadRequestException("No cards");

        CreditCardRecord card = cards.get(0);
        RewardRule rule = ruleRepo
            .findActiveRulesForCardCategory(card.getId(), intent.getCategory())
            .get(0);

        RecommendationRecord rec = new RecommendationRecord();
        rec.setUserId(user.getId());
        rec.setPurchaseIntentId(intentId);
        rec.setRecommendedCardId(card.getId());
        rec.setExpectedRewardValue(intent.getAmount() * rule.getMultiplier() / 100);

        return recRepo.save(rec);
    }

    public List<RecommendationRecord> getRecommendationsByUser(Long id) {
        return recRepo.findByUserId(id);
    }

    public List<RecommendationRecord> getAllRecommendations() {
        return recRepo.findAll();
    }
}
