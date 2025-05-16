package com.example.AnalyticsService.controller;

import com.example.AnalyticsService.entity.Expenses;
import com.example.AnalyticsService.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/analytics-service/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/user/{id}")
    public List<Expenses> getUserExpenses(@PathVariable Long id) {
        return expenseService.getAllUserExpenses(id);
    }

    @GetMapping("{id}")
    public Optional<Expenses> getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/monthly-report/user/{id}")
    public Map<String, List<?>> getMonthlyReport(@PathVariable Long id) {
        return expenseService.getUserExpensesOverPeriod(id, "monthly");
    }

    @GetMapping("/weekly-report/user/{id}")
    public Map<String, List<?>> getWeeklyReport(@PathVariable Long id) {
        return expenseService.getUserExpensesOverPeriod(id, "weekly");
    }


}
