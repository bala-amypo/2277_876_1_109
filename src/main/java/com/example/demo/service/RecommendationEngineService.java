package com.example.demo.service;

import com.example.demo.entity.PurchaseIntentRecord;
import java.util.List;

public interface RecommendationEngineService {

    List<String> recommend(PurchaseIntentRecord intent);
}
