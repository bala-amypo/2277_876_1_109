package com.example.demo.repository;

import com.example.demo.entity.RewardRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRuleRepository extends JpaRepository<RewardRule, Long> {
    List<RewardRule> findByActiveTrue();
    
    default List<RewardRule> findActiveRulesForCardCategory(Long cardId, String category) {
        return findByActiveTrue().stream()
                .filter(r -> r.getCardId().equals(cardId) && r.getCategory().equals(category))
                .toList();
    }
}
