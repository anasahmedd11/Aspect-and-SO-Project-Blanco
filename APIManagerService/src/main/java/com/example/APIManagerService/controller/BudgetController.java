package com.example.APIManagerService.controller;

import com.example.APIManagerService.DTO.CreateBudgetDTO;
import com.example.APIManagerService.entity.Budgets;
import com.example.APIManagerService.entity.Categories;
import com.example.APIManagerService.service.BudgetService;
import com.example.APIManagerService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
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
        var editBudget = new Budgets();
        editBudget.setCategory(new Categories());
        model.addAttribute("editBudget", editBudget);
        model.addAttribute("userId", userId);
        return "budgets";
    }

    @PostMapping("/create")
    public String createBudget(@CookieValue("active-user-id") Long userId,
                               @ModelAttribute("newBudget") Budgets budget,
                               RedirectAttributes redirectAttributes) {
        try {

            CreateBudgetDTO budgetDTO = new CreateBudgetDTO();
            budgetDTO.setCurrentAmount(budget.getCurrentAmount());
            budgetDTO.setLimitAmount(budget.getLimitAmount());
            budgetDTO.setCreatedAt(new Date()); // or use budget.getCreatedAt() if available
            budgetDTO.setExpiresAt(budget.getExpiresAt());
            budgetDTO.setCategoryId(budget.getCategory().getId());
            budgetDTO.setUserId(userId);
            budgetService.createBudget(budgetDTO);

            redirectAttributes.addFlashAttribute("successMessage", "Budget created successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // Preferably use logger
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create budget: " + e.getMessage());
        }

        return "redirect:/budgets";
    }


    @PostMapping("/update/{id}")
    public String updateBudget(@CookieValue("active-user-id") Long userId,
                               @PathVariable Long id,
                               @ModelAttribute Budgets editBudget,
                               RedirectAttributes redirectAttributes) {
        try {
            editBudget.setUserId(userId);

            // Validate dates
            if (editBudget.getExpiresAt() != null && editBudget.getCreatedAt() != null
                    && editBudget.getExpiresAt().before(editBudget.getCreatedAt())) {
                throw new IllegalArgumentException("Expiration date must be after creation date");
            }

            // Validate limit amount
            if (editBudget.getLimitAmount() <= 0) {
                throw new IllegalArgumentException("Limit amount must be greater than 0");
            }

            budgetService.updateBudget(id, editBudget);
            redirectAttributes.addFlashAttribute("successMessage", "Budget updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update budget: " + e.getMessage());
        }
        return "redirect:/budgets";
    }

    @PostMapping("/delete/{id}")
    public String deleteBudget(@CookieValue("active-user-id") Long userId,
                               @PathVariable Long id,
                               RedirectAttributes redirectAttributes) {
        try {
            budgetService.deleteBudget(id);
            redirectAttributes.addFlashAttribute("successMessage", "Budget deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete budget: " + e.getMessage());
        }
        return "redirect:/budgets";
    }
}
