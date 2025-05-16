package com.example.APIManagerService.controller;

import com.example.APIManagerService.entity.Budgets;
import com.example.APIManagerService.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController()
@RequestMapping("/budgets")
public class BudgetController {


    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/user/{userId}")
    public String getAllUserBudgets(@PathVariable Long userId, Model model) {

        List<Budgets> budgets = budgetService.getAllUserBudgets(userId);
        model.addAttribute("budgets", budgets);
        model.addAttribute("newBudget", new Budgets());
        return "budgets";
    }

    @PostMapping("/user/{userId}/create")
    public String createBudget(@PathVariable Long userId,
                               @ModelAttribute("newBudget") Budgets budget, Model model) {
        try {
            budget.setUserId(userId);
            budgetService.createBudget(budget);
            model.addAttribute("successMessage", "Budget created successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to create budget: " + e.getMessage());
        }
        return "redirect:/budgets/user/" + userId;
    }

    @PostMapping("/user/{userId}/update/{id}")
    public String updateBudget(@PathVariable Long userId,
                                @PathVariable Long id,
                                @ModelAttribute Budgets budget, Model model) {
        try {
            budget.setUserId(userId);
            budgetService.updateBudget(id, budget);
            model.addAttribute("successMessage", "Budget updated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update budget: " + e.getMessage());
        }
        return "redirect:/budgets/user/" + userId;
    }

    @PostMapping("/user/{userId}/delete/{id}")
    public String deleteBudget(@PathVariable Long userId,
                                @PathVariable Long id, Model model) {
        try {
            budgetService.deleteBudget(id);
            model.addAttribute("successMessage", "Budget deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to delete budget: " + e.getMessage());
        }
        return "redirect:/budgets/user/" + userId;
    }


}
