package com.example.demo.repository;

import java.util.List;
import com.example.demo.entity.CreditCardRecord;

public interface CreditCardRecordRepository {

    CreditCardRecord save(CreditCardRecord card);

    List<CreditCardRecord> findByUserId(Long userId);

    List<CreditCardRecord> findAll();

    // ✅ REQUIRED BY RecommendationEngineService + TESTS
    List<CreditCardRecord> findActiveCardsByUser(Long userId);
}
