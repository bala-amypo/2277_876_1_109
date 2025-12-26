package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service; 

import java.util.List;

@Service
public class RecommendationEngineServiceImpl implements RecommendationEngineService {

    private final PurchaseIntentRecordRepository intentRepo;
    private final UserProfileRepository userRepo;
    private final CreditCardRecordRepository cardRepo;
    private final RewardRuleRepository ruleRepo;
    private final RecommendationRecordRepository recRepo;

    public RecommendationEngineServiceImpl(
            PurchaseIntentRecordRepository intentRepo,
            UserProfileRepository userRepo,
            CreditCardRecordRepository cardRepo,
            RewardRuleRepository ruleRepo,
            RecommendationRecordRepository recRepo
    ){
        this.intentRepo=intentRepo;
        this.userRepo=userRepo;
        this.cardRepo=cardRepo;
        this.ruleRepo=ruleRepo;
        this.recRepo=recRepo;
    }

    @Override
    public RecommendationRecord generateRecommendation(Long intentId){

        PurchaseIntentRecord intent = intentRepo.findById(intentId)
                .orElseThrow(() -> new ResourceNotFoundException("Intent not found"));

        UserProfile user = userRepo.findById(intent.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<CreditCardRecord> cards = cardRepo.findActiveCardsByUser(user.getId());
        if(cards.isEmpty()) throw new BadRequestException("No active cards found");

        double best = -1;
        Long bestCardId=null;
        StringBuilder detail = new StringBuilder("{");

        for(CreditCardRecord card : cards){
            List<RewardRule> rules = ruleRepo.findActiveRulesForCardCategory(card.getId(), intent.getCategory());
            for(RewardRule r : rules){
                double reward = intent.getAmount() * r.getMultiplier();
                detail.append("\"card").append(card.getId()).append("\":").append(reward).append(",");
                if(reward > best){
                    best = reward;
                    bestCardId = card.getId();
                }
            }
        }

        if(bestCardId==null)
            throw new BadRequestException("No valid reward rules for category");

        RecommendationRecord rec = new RecommendationRecord();
        rec.setUserId(user.getId());
        rec.setPurchaseIntentId(intentId);
        rec.setRecommendedCardId(bestCardId);
        rec.setExpectedRewardValue(best);
        rec.setCalculationDetailsJson(detail.append("}").toString());

        return recRepo.save(rec);
    }

    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId){
        return recRepo.findByUserId(userId);
    }

    @Override
    public List<RecommendationRecord> getAllRecommendations(){
        return recRepo.findAll();
    }
}
