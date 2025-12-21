package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository repository;

    @Override
    public CreditCardRecord createCard(CreditCardRecord card) {
        return repository.save(card);
    }

    @Override
    public CreditCardRecord getCardById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Credit card not found with id: " + id));
    }

    @Override
    public List<CreditCardRecord> getAllCards() {
        return repository.findAll();
    }

    @Override
    public List<CreditCardRecord> getCardsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public CreditCardRecord updateCard(Long id, CreditCardRecord updatedCard) {
        CreditCardRecord existing = getCardById(id);
        updatedCard.setId(existing.getId()); // Important: keep the same ID
        return repository.save(updatedCard);
    }

    @Override
    public void deleteCard(Long id) {
        CreditCardRecord existing = getCardById(id);
        repository.delete(existing);
    }
}
