package com.example.AnalyticsService.controller;

import com.example.AnalyticsService.entity.Budgets;
import com.example.AnalyticsService.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics-service/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/user/{id}")
    public List<Budgets> getAllBudgets(@PathVariable Long id) {
        return budgetService.getAllBudgets(id);
    }

    @GetMapping("/{id}")
    public Budgets getBudgetById(@PathVariable Long id) {
        return budgetService.getBudgetById(id);
    }

    @GetMapping("/category/{id}")
    public List<Budgets> getBudgetsByCategoryId(@PathVariable Long id) {
        return budgetService.getBudgetsByCategoryId(id);
    }





}
