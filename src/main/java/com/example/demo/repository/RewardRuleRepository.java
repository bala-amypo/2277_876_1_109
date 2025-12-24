package com.example.demo.repository;

import com.example.demo.entity.RewardRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRuleRepository extends JpaRepository<RewardRule, Long> {

    List<RewardRule> findByActiveTrue();

    // For RecommendationEngineServiceImpl
    List<RewardRule> findByCardIdAndCategory(Long cardId, String category);

    // Optional: If needed for tests
    List<RewardRule> findByCardId(Long cardId);

    List<RewardRule> findByCategory(String category);
}
