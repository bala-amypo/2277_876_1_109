package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CreditCardController {

    private final CreditCardService service;

    public CreditCardController(CreditCardService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreditCardRecord> create(@RequestBody CreditCardRecord card){
        return ResponseEntity.ok(service.addCard(card));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CreditCardRecord>> cardsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(service.getCardsByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<CreditCardRecord>> all(){
        return ResponseEntity.ok(service.getAllCards());
    }
}
