package com.example.demo.repository;

import java.util.List;
import com.example.demo.entity.RewardRule;

public interface RewardRuleRepository {

    RewardRule save(RewardRule rule);

    List<RewardRule> findAll();

    List<RewardRule> findByActiveTrue();

    // ✅ REQUIRED BY RecommendationEngineService + TESTS
    List<RewardRule> findActiveRulesForCardCategory(Long cardId, String category);
}
