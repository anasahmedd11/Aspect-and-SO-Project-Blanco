package com.example.APIManagerService.controller;

import com.example.APIManagerService.entity.Budgets;
import com.example.APIManagerService.entity.Categories;
import com.example.APIManagerService.entity.Expenses;
import com.example.APIManagerService.service.CategoryService;
import com.example.APIManagerService.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    private CategoryService categoryService;

    @GetMapping("/user/{userId}")
    public String getExpensesPage(@PathVariable Long userId, Model model) {
        List<Expenses> expenses = expenseService.getAllExpenses(userId);
        List<Categories> categories = categoryService.getAllCategories();
        model.addAttribute("expenses", expenses);
        model.addAttribute("categories", categories);
        model.addAttribute("newExpense", new Expenses());
        model.addAttribute("userId", userId);
        return "expenses";
    }

    @PostMapping("/user/{userId}/create")
    public String createExpense(@PathVariable Long userId,
                                @ModelAttribute("newExpense") Expenses expense,
                                RedirectAttributes redirectAttributes) {
        try {
            expense.setUserId(userId);
            expenseService.createExpense(expense);
            redirectAttributes.addFlashAttribute("successMessage", "Expense created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create expense: " + e.getMessage());
        }
        return "redirect:/expenses/user/" + userId;
    }

    @PostMapping("/user/{userId}/update/{id}")
    public String updateExpense(@PathVariable Long userId,
                                @PathVariable Long id,
                                @ModelAttribute Expenses expense,
                                RedirectAttributes redirectAttributes) {
        try {
            expense.setUserId(userId);
            expenseService.updateExpense(id, expense);
            redirectAttributes.addFlashAttribute("successMessage", "Expense updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update expense: " + e.getMessage());
        }
        return "redirect:/expenses/user/" + userId;
    }

    @PostMapping("/user/{userId}/delete/{id}")
    public String deleteExpense(@PathVariable Long userId,
                                @PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            expenseService.deleteExpense(id);
            redirectAttributes.addFlashAttribute("successMessage", "Expense deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete expense: " + e.getMessage());
        }
        return "redirect:/expenses/user/" + userId;
    }
}