package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.service.CreditCardService;

import java.util.List;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRecordRepository repo;

    public CreditCardServiceImpl(CreditCardRecordRepository repo){
        this.repo = repo;
    }

    @Override
    public CreditCardRecord addCard(CreditCardRecord card){
        return repo.save(card);
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId){
        return repo.findByUserId(userId);
    }

    @Override
    public List<CreditCardRecord> getAllCards(){
        return repo.findAll();
    }
}
