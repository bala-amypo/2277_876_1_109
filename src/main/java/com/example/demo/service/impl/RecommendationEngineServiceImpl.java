package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ THIS IS CRITICAL
public class RecommendationEngineServiceImpl
        implements RecommendationEngineService {

    @Override
    public List<String> recommend(PurchaseIntentRecord intent) {
        // dummy logic for now
        return List.of(
                "Recommended Item 1",
                "Recommended Item 2",
                "Recommended Item 3"
        );
    }
}
