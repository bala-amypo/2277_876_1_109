package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;

import java.util.List;
import java.util.stream.Collectors;

public class RewardRuleServiceImpl implements RewardRuleService {

    private final RewardRuleRepository repository;

    public RewardRuleServiceImpl(RewardRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public RewardRule createRule(RewardRule rule) {
        if (rule.getMultiplier() <= 0) {
            // 🔥 REQUIRED MESSAGE
            throw new BadRequestException("Price multiplier must be > 0");
        }
        return repository.save(rule);
    }

    @Override
    public RewardRule updateRule(Long id, RewardRule updated) {
        RewardRule rule = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        rule.setCategory(updated.getCategory());
        rule.setRewardType(updated.getRewardType());
        rule.setMultiplier(updated.getMultiplier());
        rule.setActive(updated.getActive());

        return repository.save(rule);
    }

    @Override
    public List<RewardRule> getRulesByCard(Long cardId) {
        return repository.findAll().stream()
                .filter(r -> cardId.equals(r.getCardId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RewardRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public List<RewardRule> getAllRules() {
        return repository.findAll();
    }
}
