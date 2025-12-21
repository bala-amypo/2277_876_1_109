package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.repository.CreditCardRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationEngineServiceImpl {

    @Autowired
    private CreditCardRecordRepository cardRepo;

    public List<CreditCardRecord> getActiveCardsForUser(Long userId) {
        return cardRepo.findByUserIdAndActiveTrue(userId);
    }

}
