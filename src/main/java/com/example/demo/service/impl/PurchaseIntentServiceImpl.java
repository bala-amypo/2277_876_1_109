package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntent;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    private final PurchaseIntentRecordRepository repository;

    @Autowired
    public PurchaseIntentServiceImpl(PurchaseIntentRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseIntent createPurchaseIntent(PurchaseIntent intent) {
        return repository.save(intent);
    }

    @Override
    public PurchaseIntent getPurchaseIntentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PurchaseIntent not found with id: " + id));
    }

    @Override
    public List<PurchaseIntent> getAllPurchaseIntents() {
        return repository.findAll();
    }
}
