package com.example.APIManagerService.controller;

import com.example.APIManagerService.entity.Budgets;
import com.example.APIManagerService.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController()
public class BudgetController {


    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/api/budgets")
    public String getAllUserBudgets(@RequestParam Long userId, Model model) {

        List<Budgets> budgets = budgetService.getAllUserBudgets(userId);
        model.addAttribute("budgets", budgets);
        return "budgets";
    }







}
