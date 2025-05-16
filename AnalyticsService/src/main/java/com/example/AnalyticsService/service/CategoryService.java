package com.example.AnalyticsService.service;

import com.example.AnalyticsService.DTO.CategoryExpenseDTO;
import com.example.AnalyticsService.entity.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    private final RestTemplate restTemplate;
    private final String databaseServiceUrl = "http://Blanco-Database-Service:8081/db-service/expenses";

    @Autowired
    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, List<?>> getBarChartData(Long id) {
        String url = databaseServiceUrl + "/user/" + id + "/expenses-by-category";
        ResponseEntity<CategoryExpenseDTO[]> response = restTemplate.getForEntity(url, CategoryExpenseDTO[].class);
        List<String> categories = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();

        if (response.getBody() != null) {
            for (CategoryExpenseDTO dto : response.getBody()) {
                categories.add(dto.getCategoryName());
                amounts.add(dto.getTotalAmount());
            }
        }

        Map<String, List<?>> result = new HashMap<>();
        result.put("categories", categories);
        result.put("amounts", amounts);
        return result;
    }
}