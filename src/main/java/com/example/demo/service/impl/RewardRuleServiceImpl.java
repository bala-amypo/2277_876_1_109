package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ CRITICAL
public class RewardRuleServiceImpl implements RewardRuleService {

    private final RewardRuleRepository rewardRuleRepository;

    public RewardRuleServiceImpl(
            RewardRuleRepository rewardRuleRepository) {
        this.rewardRuleRepository = rewardRuleRepository;
    }

    @Override
    public RewardRule createRule(RewardRule rule) {
        return rewardRuleRepository.save(rule);
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
    public void deleteRule(Long id) {
        rewardRuleRepository.deleteById(id);
    }
}
