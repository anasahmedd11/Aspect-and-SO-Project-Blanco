package com.example.APIManagerService.controller;
import com.example.APIManagerService.entity.Budgets;
import com.example.APIManagerService.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        model.addAttribute("userId", userId);
        return "budgets";
    }

    @PostMapping("/user/{userId}/create")
    public String createBudget(@PathVariable Long userId,
                               @ModelAttribute("newBudget") Budgets budget, RedirectAttributes redirectAttributes) {
        try {
            budget.setUserId(userId);
            budgetService.createBudget(budget);
            redirectAttributes.addFlashAttribute("successMessage", "Budget created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create budget: " + e.getMessage());
        }
        return "redirect:/budgets/user/" + userId;
    }

    @PostMapping("/user/{userId}/update/{id}")
    public String updateBudget(@PathVariable Long userId,
                               @PathVariable Long id,
                               @ModelAttribute Budgets budget,
                               RedirectAttributes redirectAttributes) {
        try {
            budget.setUserId(userId);
            budgetService.updateBudget(id, budget);
            redirectAttributes.addFlashAttribute("successMessage", "Budget updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update budget: " + e.getMessage());
        }
        return "redirect:/budgets/user/" + userId;
    }

    @PostMapping("/user/{userId}/delete/{id}")
    public String deleteBudget(@PathVariable Long userId,
                               @PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            budgetService.deleteBudget(id);
            redirectAttributes.addFlashAttribute("successMessage", "Budget deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete budget: " + e.getMessage());
        }
        return "redirect:/budgets/user/" + userId;
    }


}
