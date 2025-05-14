package com.example.AnalyticsService.service;

import com.example.AnalyticsService.entity.Budgets;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryService {

    private final RestTemplate restTemplate;
    private final String databaseServiceUrl = "http://localhost:8080/db-service/categories";

    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
