package com.example.MoneyManagementService.service;

import com.example.MoneyManagementService.DTO.BudgetDTO;
import com.example.MoneyManagementService.entity.Budgets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    private final RestTemplate restTemplate;
    private final String databaseServiceUrl = "http://Blanco-Database-Service:8081/db-service/budgets";

    @Autowired
    public BudgetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Budgets> getAllUserBudgets(Long userId) {
        String url = databaseServiceUrl + "/user/" + userId;
        ResponseEntity<Budgets[]> response = restTemplate.getForEntity(url, Budgets[].class);
        return Arrays.asList(response.getBody());
    }

    public Optional<Budgets> getBudgetById(Long id) {
        String url = databaseServiceUrl + "/" + id;
        try {
            ResponseEntity<Budgets> response = restTemplate.getForEntity(url, Budgets.class);
            return Optional.ofNullable(response.getBody());
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Optional.empty();
            }
            throw e;
        }
    }

    public Budgets createBudget(BudgetDTO budgetDTO) {
        String url = databaseServiceUrl;
        ResponseEntity<Budgets> response = restTemplate.postForEntity(url, budgetDTO, Budgets.class);
        return response.getBody();
    }

    public List<Budgets> updateBudget(Long id, BudgetDTO updateBudgetDTO) {
        String url = databaseServiceUrl + "/" + id;
        HttpEntity<BudgetDTO> requestEntity = new HttpEntity<>(updateBudgetDTO);
        try {
            ResponseEntity<Budgets[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    requestEntity,
                    Budgets[].class
            );
            Budgets[] budgetsArray = response.getBody();
            return budgetsArray != null ? Arrays.asList(budgetsArray) : List.of();
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return List.of(); // return empty list if not found
            }
            throw e;
        }
    }

    public boolean deleteBudget(Long id) {
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
}
