package com.example.AnalyticsService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExpenseService {

    private final String baseurl = "http://localhost:8080/db-service/expenses";
    private final RestTemplate restTemplate;

    @Autowired
    public ExpenseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



}
