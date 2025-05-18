package com.example.AnalyticsService.service;

import com.example.AnalyticsService.entity.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ExpenseService {

    private final String baseurl = "http://Blanco-Database-Service:8081/db-service/expenses";
    private final RestTemplate restTemplate;

    @Autowired
    public ExpenseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<Expenses> getAllUserExpenses(Long userId) {
        String url = baseurl + "/user/" + userId;
        ResponseEntity<Expenses[]> response = restTemplate.getForEntity(url, Expenses[].class);
        return Arrays.asList(response.getBody());
    }

    public Optional<Expenses> getExpenseById(Long id) {
        String url = baseurl + "/" + id;
        try {
            ResponseEntity<Expenses> response = restTemplate.getForEntity(url, Expenses.class);
            return Optional.ofNullable(response.getBody());
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Optional.empty();
            }
            throw e;
        }
    }

    public Map<String, List<?>> getUserExpensesOverPeriod(Long userId) {
        String url = baseurl + "/monthly-report/user/" + userId;
        ResponseEntity<Expenses[]> response = restTemplate.getForEntity(url, Expenses[].class);
        List<String> dates = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();
        if (response.getBody() != null) {
            for (Expenses expense : response.getBody()) {
                dates.add(expense.getDate().toString());
                amounts.add(expense.getAmount());
            }
        }
        Map<String, List<?>> result = new HashMap<>();
        result.put("dates", dates);
        result.put("amounts", amounts);
        return result;
    }


}
