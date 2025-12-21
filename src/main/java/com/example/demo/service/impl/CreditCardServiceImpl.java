package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRecordRepository cardRepo;

    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {
        return cardRepo.save(card);
    }

    @Override
    public CreditCardRecord getCardById(Long id) {
        return cardRepo.findById(id).orElse(null);
    }

    @Override
    public List<CreditCardRecord> getAllCards() {
        return cardRepo.findAll();
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return cardRepo.findByUserId(userId);
    }

    @Override
    public List<CreditCardRecord> getActiveCardsByUser(Long userId) {
        return cardRepo.findByUserIdAndActiveTrue(userId);
    }

    @Override
    public CreditCardRecord updateCard(CreditCardRecord card) {
        return cardRepo.save(card);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepo.deleteById(id);
    }
}
