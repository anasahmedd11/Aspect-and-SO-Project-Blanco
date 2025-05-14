package com.example.AnalyticsService.controller;

import com.example.AnalyticsService.entity.Expenses;
import com.example.AnalyticsService.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics-service/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

//    public ResponseEntity<List<Expenses>> getUserDailyExpenses(Long userId) {
//
//    }
}
