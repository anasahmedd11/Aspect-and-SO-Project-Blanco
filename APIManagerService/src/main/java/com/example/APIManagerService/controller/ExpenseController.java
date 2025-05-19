package com.example.APIManagerService.controller;

import com.example.APIManagerService.entity.Categories;
import com.example.APIManagerService.entity.Expenses;
import com.example.APIManagerService.service.CategoryService;
import com.example.APIManagerService.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseService expenseService;
    private CategoryService categoryService;

    public ExpenseController(ExpenseService expenseService, CategoryService categoryService) {
        this.expenseService = expenseService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String getExpensesPage(@CookieValue("active-user-id") Long userId, Model model) {
        List<Expenses> expenses = expenseService.getAllExpenses(userId);
        List<Categories> categories = categoryService.getAllCategories();
        model.addAttribute("expenses", expenses);
        model.addAttribute("categories", categories);
        model.addAttribute("newExpense", new Expenses());
        model.addAttribute("editExpense", new Expenses());
        model.addAttribute("userId", userId);
        return "expenses";
    }

    @PostMapping("/create")
    public String createExpense(@CookieValue("active-user-id") Long userId,
                                @ModelAttribute("expense") Expenses expense,
                                RedirectAttributes redirectAttributes) {
        try {
            expense.setUserId(userId);
            expenseService.createExpense(expense);
            redirectAttributes.addFlashAttribute("successMessage", "Expense created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create expense: " + e.getMessage());
        }
        return "redirect:/expenses";
    }

    @PostMapping("/update/{id}")
    public String updateExpense(@CookieValue("active-user-id") Long userId,
                                @PathVariable Long id,
                                @ModelAttribute("editExpense") Expenses editExpense,
                                RedirectAttributes redirectAttributes) {
        try {
            editExpense.setUserId(userId);
            expenseService.updateExpense(id, editExpense);
            redirectAttributes.addFlashAttribute("successMessage", "Expense updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update expense: " + e.getMessage());
        }
        return "redirect:/expenses";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@CookieValue("active-user-id") Long userId,
                                @PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            expenseService.deleteExpense(id);
            redirectAttributes.addFlashAttribute("successMessage", "Expense deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete expense: " + e.getMessage());
        }
        return "redirect:/expenses";
    }
}