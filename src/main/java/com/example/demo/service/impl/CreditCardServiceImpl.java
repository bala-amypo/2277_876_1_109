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
    public CreditCardRecord createCard(CreditCardRecord card) {
        return cardRepo.save(card);
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
    public CreditCardRecord updateCard(Long cardId, CreditCardRecord card) {
        CreditCardRecord existing = cardRepo.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found with id: " + cardId));

        existing.setCardNumber(card.getCardNumber());
        existing.setExpiryDate(card.getExpiryDate());
        existing.setCvv(card.getCvv());
        existing.setActive(card.isActive());

        return cardRepo.save(existing);
    }

    @Override
    public void deleteCard(Long cardId) {
        CreditCardRecord existing = cardRepo.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found with id: " + cardId));
        cardRepo.delete(existing);
    }
}
