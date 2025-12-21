package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {

    private final RewardRuleRepository rewardRuleRepository;

    public RewardRuleServiceImpl(RewardRuleRepository rewardRuleRepository) {
        this.rewardRuleRepository = rewardRuleRepository;
    }

    @Override
    public RewardRule createRule(RewardRule rule) {
        return rewardRuleRepository.save(rule);
    }

    @Override
    public RewardRule updateRule(Long id, RewardRule rule) {
        RewardRule existing = rewardRuleRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        existing.setRuleName(rule.getRuleName());
        existing.setPoints(rule.getPoints());
        existing.setActive(rule.isActive());
        existing.setCardId(rule.getCardId());

        return rewardRuleRepository.save(existing);
    }

    @Override
    public RewardRule getRuleById(Long id) {
        return rewardRuleRepository.findById(id).orElse(null);
    }

    @Override
    public List<RewardRule> getAllRules() {
        return rewardRuleRepository.findAll();
    }

    @Override
    public List<RewardRule> getRulesByCard(Long cardId) {
        return rewardRuleRepository.findByCardId(cardId);
    }

    @Override
    public List<RewardRule> getActiveRules() {
        return rewardRuleRepository.findByActiveTrue();
    }

    @Override
    public void deleteRule(Long id) {
        rewardRuleRepository.deleteById(id);
    }
}
