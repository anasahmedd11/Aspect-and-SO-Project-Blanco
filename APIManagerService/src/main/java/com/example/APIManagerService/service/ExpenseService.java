package com.example.APIManagerService.service;

import com.example.APIManagerService.entity.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    private final RestTemplate restTemplate;
    private final String moneyManagementServiceUrl = "http://Blanco-Money-Management-Service:8083/money-management/expenses";
    private final String analyticsService = "http://Blanco-Analytics-Service:8084/analytics-service/expenses";

    @Autowired
    public ExpenseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, List<?>> getMonthlyExpenseReport(Long id) {
        String url = analyticsService + "/monthly-report/user/" + id;
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }


    public List<Expenses> getAllExpenses(Long userId) {
        String url = moneyManagementServiceUrl + "/user/" + userId;
        ResponseEntity<Expenses[]> response = restTemplate.getForEntity(url, Expenses[].class);
        return List.of(response.getBody());
    }

    public Expenses getExpenseById(Long id) {
        String url = moneyManagementServiceUrl + "/" + id;
        return restTemplate.getForObject(url, Expenses.class);
    }

    public Expenses createExpense(Expenses expense) {
        String url = moneyManagementServiceUrl;
        return restTemplate.postForObject(url, expense, Expenses.class);
    }

    public void updateExpense(Long id, Expenses expense) {
        String url = moneyManagementServiceUrl + "/" + id;
        restTemplate.put(url, expense);
    }

    public void deleteExpense(Long id) {
        String url = moneyManagementServiceUrl + "/" + id;
        restTemplate.delete(url);
    }
}