package com.example.APIManagerService.controller;

import com.example.APIManagerService.entity.Expenses;
import com.example.APIManagerService.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final ExpenseService expenseService;

    public ReportController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/monthly/{id}")
    public String getMonthlyReports(Model model, @PathVariable Long id) {
        List<Expenses> monthlyExpenses = expenseService.getMonthlyExpenseReport(id);
        System.out.println("Monthly Expenses");
        List<Double> amounts = new ArrayList<>();
        for (Expenses expenses : monthlyExpenses) {
            amounts.add(expenses.getAmount());
        }
        model.addAttribute("monthlyExpenses", amounts);
        return "reports";
    }

    @GetMapping("/weekly/{id}")
    public String getWeeklyReports(Model model, @PathVariable Long id) {
        List<Expenses> monthlyExpenses = expenseService.getWeeklyExpenseReport(id);
        System.out.println(monthlyExpenses);
        return "reports";
    }

}