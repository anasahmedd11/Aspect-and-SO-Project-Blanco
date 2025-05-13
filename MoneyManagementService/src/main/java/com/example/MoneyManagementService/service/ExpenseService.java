package com.example.MoneyManagementService.service;

import com.example.MoneyManagementService.DTO.ExpenseDTO;
import com.example.MoneyManagementService.entity.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final RestTemplate restTemplate;
    // Replace with your actual DatabaseService host and port
    private final String databaseServiceUrl = "/db-service/expenses";

    @Autowired
    public ExpenseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Expenses> getAllUserExpenses(Long userId) {
        String url = databaseServiceUrl + "/user/" + userId;
        ResponseEntity<Expenses[]> response = restTemplate.getForEntity(url, Expenses[].class);
        return Arrays.asList(response.getBody());
    }

    public Optional<Expenses> getExpenseById(Long id) {
        String url = databaseServiceUrl + "/" + id;
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

    public Expenses createExpense(ExpenseDTO expenseDTO) {
        String url = databaseServiceUrl;
        ResponseEntity<Expenses> response = restTemplate.postForEntity(url, expenseDTO, Expenses.class);
        return response.getBody();
    }

    public Expenses updateExpense(Long id, ExpenseDTO updateExpenseDTO) {
        String url = databaseServiceUrl + "/" + id;
        HttpEntity<ExpenseDTO> requestEntity = new HttpEntity<>(updateExpenseDTO);
        try {
            ResponseEntity<Expenses> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    requestEntity,
                    Expenses.class
            );
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }

    public boolean deleteExpense(Long id) {
        String url = databaseServiceUrl + "/" + id;
        try {
            restTemplate.delete(url);
            return true;
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            }
            throw e;
        }
    }

    public List<Expenses> getExpensesByCategory(Long userId, Long categoryId) {
        String url = databaseServiceUrl + "/user/" + userId + "/category/" + categoryId;
        ResponseEntity<Expenses[]> response = restTemplate.getForEntity(url, Expenses[].class);
        return Arrays.asList(response.getBody());
    }

    public List<Expenses> getExpensesByDateRange(Long userId, String startDate, String endDate) {
        String url = databaseServiceUrl + "/user/" + userId + "/date-range?startDate=" + startDate + "&endDate=" + endDate;
        ResponseEntity<Expenses[]> response = restTemplate.getForEntity(url, Expenses[].class);
        return Arrays.asList(response.getBody());
    }
}
