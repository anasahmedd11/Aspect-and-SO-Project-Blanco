package com.example.MoneyManagementService.service;

import com.example.MoneyManagementService.DTO.TransactionDTO;
import com.example.MoneyManagementService.entity.Categories;
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
    private final String databaseServiceTransactionUrl = "http://localhost:8080/db-service/expenses/transaction";

    @Autowired
    public TransactionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Transactions createTransaction(TransactionDTO transactionDTO) {
        return restTemplate.postForObject(databaseServiceTransactionUrl, transactionDTO, Transactions.class);
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

    public Transactions updateTransaction(Long id, TransactionDTO transactionDTO) {
        ResponseEntity<Transactions> response = restTemplate.exchange(
                databaseServiceTransactionUrl + id,
                HttpMethod.PUT,
                new HttpEntity<>(transactionDTO),
                Transactions.class
        );
        return response.getBody();
    }

    public void deleteTransaction(Long id) {
        restTemplate.delete(databaseServiceTransactionUrl + "/" + id);
    }
}
