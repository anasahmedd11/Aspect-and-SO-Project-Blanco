package com.example.APIManagerService.service;

import com.example.APIManagerService.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TransactionService {
    private final RestTemplate restTemplate;
    private final String moneyManagementServiceUrl = "http://Blanco-Money-Management-Service:8083/money-management/transactions";

    @Autowired
    public TransactionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Transactions> getAllTransactions(Long userId) {
        String url = moneyManagementServiceUrl + "/user/" + userId;
        ResponseEntity<List<Transactions>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transactions>>() {}
        );
        return response.getBody();
    }

    public Transactions getTransactionById(Long id) {
        String url = moneyManagementServiceUrl + "/" + id;
        return restTemplate.getForObject(url, Transactions.class);
    }

    public void createTransaction(Transactions transaction) {
        String url = moneyManagementServiceUrl;
        restTemplate.postForObject(url, transaction, Transactions.class);
    }

    public void updateTransaction(Long id, Transactions transaction) {
        String url = moneyManagementServiceUrl + "/" + id;
        restTemplate.put(url, transaction);
    }

    public void deleteTransaction(Long id) {
        String url = moneyManagementServiceUrl + "/" + id;
        restTemplate.delete(url);
    }
}
