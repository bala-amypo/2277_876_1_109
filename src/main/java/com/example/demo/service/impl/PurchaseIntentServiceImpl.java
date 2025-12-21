package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntent;
import com.example.demo.repository.PurchaseIntentRepository;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    private final PurchaseIntentRepository repository;

    @Autowired
    public PurchaseIntentServiceImpl(PurchaseIntentRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseIntent createPurchaseIntent(PurchaseIntent intent) {
        return repository.save(intent);
    }

    @Override
    public PurchaseIntent getPurchaseIntentById(Long id) {
        Optional<PurchaseIntent> optional = repository.findById(id);
        return optional.orElseThrow(() -> new RuntimeException("PurchaseIntent not found"));
    }

    @Override
    public List<PurchaseIntent> getAllPurchaseIntents() {
        return repository.findAll();
    }
}
