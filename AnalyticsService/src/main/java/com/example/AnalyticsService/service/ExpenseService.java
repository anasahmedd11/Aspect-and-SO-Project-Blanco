package com.example.AnalyticsService.service;

import com.example.AnalyticsService.entity.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public List<Expenses> getUserMonthlyExpenses(Long userId) {
        String url = baseurl + "/monthly-report/user/" + userId;
        ResponseEntity<Expenses[]> response = restTemplate.getForEntity(url, Expenses[].class);
        return Arrays.asList(response.getBody());
    }

    public List<Expenses> getUserWeeklyExpenses(Long userId) {
        String url = baseurl + "/weekly-report/user/" + userId;
        ResponseEntity<Expenses[]> response = restTemplate.getForEntity(url, Expenses[].class);
        return Arrays.asList(response.getBody());
    }

}
