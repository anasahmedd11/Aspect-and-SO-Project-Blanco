package com.example.MoneyManagementService.service;

import com.example.MoneyManagementService.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TransactionService {
    private final RestTemplate restTemplate;
    private final String databaseServiceTransactionUrl = "http://Blanco-Database-Service:8081/db-service/transaction";

    @Autowired
    public TransactionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Transactions createTransaction(Transactions transaction) {
        return restTemplate.postForObject(databaseServiceTransactionUrl, transaction, Transactions.class);
    }

    public List<Transactions> getAllTransactions(Long userId) {
        return restTemplate.exchange(
                databaseServiceTransactionUrl + "/user/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transactions>>() {
                }
        ).getBody();
    }

    public Transactions updateTransaction(Long id, Transactions transaction) {
        ResponseEntity<Transactions> response = restTemplate.exchange(
                databaseServiceTransactionUrl + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(transaction),
                Transactions.class
        );
        return response.getBody();
    }

    public void deleteTransaction(Long id) {
        restTemplate.delete(databaseServiceTransactionUrl + "/" + id);
    }
}
