package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CreditCardController {

    private final CreditCardService cardService;

    public CreditCardController(CreditCardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CreditCardRecord> addCard(@RequestBody CreditCardRecord card) {
        CreditCardRecord saved = cardService.addCard(card);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CreditCardRecord>> getCardsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cardService.getCardsByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<CreditCardRecord>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }
}
