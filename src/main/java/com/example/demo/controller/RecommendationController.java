package com.example.demo.controller;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationEngineService recommendationService;

    public RecommendationController(
            RecommendationEngineService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public List<String> recommend(
            @RequestBody PurchaseIntentRecord intent) {
        return recommendationService.recommend(intent);
    }
}
