package com.example.APIManagerService.service;

import com.example.APIManagerService.entity.Budgets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BudgetService {

    private final String baseUrl = "http://Blanco-Money-Management-Service:8083/money-management/budgets";
    private final RestTemplate restTemplate;

    @Autowired
    public BudgetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Add methods to interact with the Money Management Service API

    public List<Budgets> getAllUserBudgets(Long userId) {
        String url = baseUrl + "/user/" + userId;
        Budgets[] budgets = restTemplate.getForObject(url, Budgets[].class);
        return List.of(budgets);
    }

    public Budgets getBudgetById(Long id) {
        String url = baseUrl + "/" + id;
        return restTemplate.getForObject(url, Budgets.class);
    }

    public void createBudget(Budgets budget) {
        String url = baseUrl;
        restTemplate.postForObject(url, budget, Budgets.class);
    }

    public void updateBudget(Long id, Budgets budget) {
        String url = baseUrl + "/" + id;
        restTemplate.put(url, budget);
    }

    public void deleteBudget(Long id) {
        String url = baseUrl + "/" + id;
        restTemplate.delete(url);
    }








}
