package com.example.APIManagerService.service;

import com.example.APIManagerService.entity.Budgets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.APIManagerService.DTO.Authentication.BudgetDTO;

import java.util.Arrays;
import java.util.List;

@Service
public class BudgetService {

    private final String baseUrl = "http://Blanco-Money-Management-Service:8083/money-management/budgets";
    private final RestTemplate restTemplate;

    @Autowired
    public BudgetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Budgets> getAllUserBudgets(Long userId) {
        String url = baseUrl + "/user/" + userId;
        ResponseEntity<Budgets[]> response = restTemplate.getForEntity(url, Budgets[].class);
        return Arrays.asList(response.getBody());
    }

    public Budgets getBudgetById(Long id) {
        String url = baseUrl + "/" + id;
        return restTemplate.getForObject(url, Budgets.class);
    }

    public void createBudget(BudgetDTO budgetDTO) {

        ResponseEntity<Budgets> response = restTemplate.postForEntity(baseUrl, budgetDTO, Budgets.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Budget created successfully: " + response.getBody());
        } else {
            System.out.println("Failed to create budget: " + response.getStatusCode());
        }
    }

    public void updateBudget(Long id, Budgets budget) {
        String url = baseUrl + "/" + id;
        // Convert Budgets entity to BudgetDTO
        var budgetDTO = new BudgetDTO(
                budget.getCurrentAmount(),
                budget.getLimitAmount(),
                budget.getCreatedAt(),
                budget.getExpiresAt(),
                budget.getUserId(),
                budget.getCategoryId()
        );


        HttpEntity<BudgetDTO> requestEntity = new HttpEntity<>(budgetDTO);
        ResponseEntity<Budgets[]> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Budgets[].class
        );
    }

    public void deleteBudget(Long id) {
        String url = baseUrl + "/" + id;
        restTemplate.delete(url);
    }
}
