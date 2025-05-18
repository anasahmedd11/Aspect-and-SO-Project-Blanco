package com.example.APIManagerService.controller;
import com.example.APIManagerService.entity.Budgets;
import com.example.APIManagerService.entity.Categories;
import com.example.APIManagerService.service.BudgetService;
import com.example.APIManagerService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller()
@RequestMapping("/budgets")
public class BudgetController {


    private final BudgetService budgetService;
    private final CategoryService categoryService;

    @Autowired
    public BudgetController(BudgetService budgetService, CategoryService categoryService) {
        this.budgetService = budgetService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String getAllUserBudgets(@CookieValue("active-user-id") Long userId, Model model) {

        List<Budgets> budgets = budgetService.getAllUserBudgets(userId);
        List<Categories> categories = categoryService.getAllCategories();
        model.addAttribute("budgets", budgets);
        model.addAttribute("categories", categories);
        model.addAttribute("newBudget", new Budgets());
        model.addAttribute("userId", userId);
        return "budgets";
    }

    @PostMapping("/create")
    public String createBudget(@CookieValue("active-user-id") Long userId,
                               @ModelAttribute("newBudget") Budgets budget, RedirectAttributes redirectAttributes) {
        try {
            budget.setUserId(userId);
            budgetService.createBudget(budget);
            redirectAttributes.addFlashAttribute("successMessage", "Budget created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create budget: " + e.getMessage());
        }
        return "redirect:/budgets";
    }

    @PostMapping("/update/{id}")
    public String updateBudget(@CookieValue("active-user-id") Long userId,
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
        return "redirect:/budgets";
    }

    @PostMapping("/delete/{id}")
    public String deleteBudget(@CookieValue("active-user-id") Long userId,
                               @PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            budgetService.deleteBudget(id);
            redirectAttributes.addFlashAttribute("successMessage", "Budget deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete budget: " + e.getMessage());
        }
        return "redirect:/budgets";
    }


}
