package com.example.demo.service;

import com.example.demo.entity.CreditCardRecord;
import java.util.List;

public interface CreditCardService {

    CreditCardRecord createCard(CreditCardRecord card);

    List<CreditCardRecord> getCardsByUser(Long userId);

    List<CreditCardRecord> getActiveCardsByUser(Long userId);

    CreditCardRecord updateCard(Long cardId, CreditCardRecord card);

    void deleteCard(Long cardId);
}
