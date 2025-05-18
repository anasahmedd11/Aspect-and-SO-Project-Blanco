package com.example.APIManagerService.controller;

import com.example.APIManagerService.aspects.RateLimiting;
import com.example.APIManagerService.entity.Budgets;
import com.example.APIManagerService.entity.Expenses;
import com.example.APIManagerService.entity.Transactions;
import com.example.APIManagerService.service.BudgetService;
import com.example.APIManagerService.service.ExpenseService;
import com.example.APIManagerService.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final TransactionService transactionService;
    private final BudgetService budgetService;
    private final ExpenseService expenseService;

    @Autowired
    public HomeController(TransactionService transactionService,
                          BudgetService budgetService,
                          ExpenseService expenseService) {
        this.transactionService = transactionService;
        this.budgetService = budgetService;
        this.expenseService = expenseService;
    }

    @GetMapping("/home")
    @RateLimiting
    public String getHomePage(@CookieValue("active-user-id") Long userId, Model model) {
        List<Transactions> transactions = transactionService.getAllTransactions(userId);
        List<Budgets> budgets = budgetService.getAllUserBudgets(userId);
        List<Expenses> expenses = expenseService.getAllExpenses(userId);

        model.addAttribute("transactions", transactions);
        model.addAttribute("budgets", budgets);
        model.addAttribute("expenses", expenses);
        model.addAttribute("userId", userId);
        return "home";
    }
}


