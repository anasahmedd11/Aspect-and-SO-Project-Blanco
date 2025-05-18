package com.example.APIManagerService.controller;

import com.example.APIManagerService.entity.Transactions;
import com.example.APIManagerService.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public String getTransactionsPage(@CookieValue("active-user-id") Long userId, Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions(userId));
        model.addAttribute("newTransaction", new Transactions());
        model.addAttribute("userId", userId);
        return "transactions";
    }

    @PostMapping("/create")
    public String createTransaction(@CookieValue("active-user-id") Long userId,
                                    @ModelAttribute("newTransaction") Transactions transaction,
                                    RedirectAttributes redirectAttributes) {
        try {
            transactionService.createTransaction(transaction);
            redirectAttributes.addFlashAttribute("successMessage", "Transaction created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create transaction: " + e.getMessage());
        }
        return "redirect:/transactions";
    }

    @PostMapping("/update/{id}")
    public String updateTransaction(@CookieValue("active-user-id") Long userId,
                                    @PathVariable Long id,
                                    @ModelAttribute Transactions transaction,
                                    RedirectAttributes redirectAttributes) {
        try {
            transactionService.updateTransaction(id, transaction);
            redirectAttributes.addFlashAttribute("successMessage", "Transaction updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update transaction: " + e.getMessage());
        }
        return "redirect:/transactions";
    }

    @PostMapping("/user/{userId}/delete/{id}")
    public String deleteTransaction(@PathVariable Long userId,
                                    @PathVariable Long id,
                                    RedirectAttributes redirectAttributes) {
        try {
            transactionService.deleteTransaction(id);
            redirectAttributes.addFlashAttribute("successMessage", "Transaction deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete transaction: " + e.getMessage());
        }
        return "redirect:/transactions";
    }
}
