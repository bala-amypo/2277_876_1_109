package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;
import org.springframework.stereotype.Service; 

import java.util.List;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {

    private final RewardRuleRepository repo;

    public RewardRuleServiceImpl(RewardRuleRepository repo){
        this.repo = repo;
    }

    @Override
    public RewardRule createRule(RewardRule rule){
        if(rule.getMultiplier()==null || rule.getMultiplier()<=0)
            throw new BadRequestException("Price multiplier must be > 0");
        return repo.save(rule);
    }

    @Override
    public List<RewardRule> getActiveRules(){ return repo.findByActiveTrue(); }

    @Override
    public List<RewardRule> getAllRules(){ return repo.findAll(); }
}
