package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CreditCardController {

    @Autowired
    private CreditCardService cardService;

    @PostMapping
    public CreditCardRecord addCard(@RequestBody CreditCardRecord card) {
        return cardService.addCard(card);
    }

    @GetMapping("/{id}")
    public CreditCardRecord getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @GetMapping
    public List<CreditCardRecord> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/user/{userId}")
    public List<CreditCardRecord> getCardsByUser(@PathVariable Long userId) {
        return cardService.getCardsByUser(userId);
    }

    @GetMapping("/user/{userId}/active")
    public List<CreditCardRecord> getActiveCardsByUser(@PathVariable Long userId) {
        return cardService.getActiveCardsByUser(userId);
    }

    @PutMapping("/{id}")
    public CreditCardRecord updateCard(@PathVariable Long id, @RequestBody CreditCardRecord card) {
        card.setId(id);
        return cardService.updateCard(card);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
