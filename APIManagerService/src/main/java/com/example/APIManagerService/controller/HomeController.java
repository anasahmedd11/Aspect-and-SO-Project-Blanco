package com.example.APIManagerService.controller;

import com.example.APIManagerService.entity.Budgets;
import com.example.APIManagerService.entity.Expenses;
import com.example.APIManagerService.entity.Transactions;
import com.example.APIManagerService.service.BudgetService;
import com.example.APIManagerService.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final BudgetService budgetService;
    private final ExpenseService expenseService;

    @Autowired
    public HomeController(
                          BudgetService budgetService,
                          ExpenseService expenseService) {
        this.budgetService = budgetService;
        this.expenseService = expenseService;
    }

    @GetMapping("/home")
    public String getHomePage(@CookieValue("active-user-id") Long userId, Model model) {
        List<Budgets> budgets = budgetService.getAllUserBudgets(userId);
        List<Expenses> expenses = expenseService.getAllExpenses(userId);

        model.addAttribute("budgets", budgets);
        model.addAttribute("expenses", expenses);
        model.addAttribute("userId", userId);
        return "home";
    }
}


