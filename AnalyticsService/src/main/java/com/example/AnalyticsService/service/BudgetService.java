package com.example.AnalyticsService.service;

import com.example.AnalyticsService.entity.Budgets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class BudgetService {

    String baseurl = "http://localhost:8080/db-service/budgets";

    private final RestTemplate restTemplate;

    @Autowired
    public BudgetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Budgets> getAllBudgets(Long userId) {
        String url = baseurl + "/user/" + userId;
        Budgets[] budgets = restTemplate.getForObject(url, Budgets[].class);
        return List.of(budgets);
    }

    public Budgets getBudgetById(Long id) {
        String url = baseurl + "/" + id;
        return restTemplate.getForObject(url, Budgets.class);
    }

    public List<Budgets> getBudgetsByCategoryId(Long categoryId) {
        String url = baseurl + "/category/" + categoryId;
        Budgets[] budgets = restTemplate.getForObject(url, Budgets[].class);
        return List.of(budgets);
    }







}
