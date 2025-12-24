package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.repository.CreditCardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CreditCardController {

    private final CreditCardRepository repository;

    public CreditCardController(CreditCardRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public CreditCardRecord create(@RequestBody CreditCardRecord card) {
        return repository.save(card);
    }

    @GetMapping
    public List<CreditCardRecord> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CreditCardRecord getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    @PutMapping("/{id}")
    public CreditCardRecord update(
            @PathVariable Long id,
            @RequestBody CreditCardRecord updatedCard) {

        CreditCardRecord existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        existing.setCardName(updatedCard.getCardName());
        existing.setIssuer(updatedCard.getIssuer());
        existing.setCardType(updatedCard.getCardType());
        existing.setAnnualFee(updatedCard.getAnnualFee());
        existing.setStatus(updatedCard.getStatus());

        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Card deleted");
    }
}
