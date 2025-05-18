package com.example.APIManagerService.controller;

import com.example.APIManagerService.service.CategoryService;
import com.example.APIManagerService.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final ExpenseService expenseService;
    private final CategoryService categoryService;

    public ReportController(ExpenseService expenseService, CategoryService categoryService) {
        this.expenseService = expenseService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public String getReports(Model model, @PathVariable Long id) {
        // Getting Expenses Over Time data
        Map<String, List<?>> monthlyExpenses = expenseService.getMonthlyExpenseReport(id);
        List<String> monthlyDates = (List<String>) monthlyExpenses.get("dates");
        List<Double> monthlyAmount = (List<Double>) monthlyExpenses.get("amounts");

        //Adding the received data into Model for Thymeleaf
        model.addAttribute("dates", monthlyDates);
        model.addAttribute("monthlyExpenses", monthlyAmount);

        // Getting Expense Categories Bar Chart data
        Map<String, List<?>> monthlyBarCharData = categoryService.getExpenseBarChartDate(id);
        List<Double> barDataAmounts = (List<Double>) monthlyBarCharData.get("amounts");
        List<String> barDataCategories = (List<String>) monthlyBarCharData.get("categories");

        //Adding the received data into Model for Thymeleaf
        model.addAttribute("barAmounts", barDataAmounts);
        model.addAttribute("categories", barDataCategories);
        model.addAttribute("userId", id);
        return "reports";
    }

}