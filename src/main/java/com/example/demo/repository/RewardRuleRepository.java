package com.example.demo.repository;

import com.example.demo.entity.RewardRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRuleRepository
        extends JpaRepository<RewardRule, Long> {
}
