package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.service.PurchaseIntentService;

import java.util.List;

public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    private final PurchaseIntentRecordRepository repository;

    public PurchaseIntentServiceImpl(PurchaseIntentRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        if (intent.getAmount() <= 0) {
            throw new BadRequestException("Amount must be greater than 0");
        }
        return repository.save(intent);
    }

    @Override
    public PurchaseIntentRecord getIntentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Intent not found"));
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<PurchaseIntentRecord> getAllIntents() {
        return repository.findAll();
    }
}
