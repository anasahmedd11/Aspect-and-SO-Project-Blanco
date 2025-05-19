package com.example.APIManagerService.controller;

import com.example.APIManagerService.DTO.Authentication.TransactionDTO;
import com.example.APIManagerService.entity.Expenses;
import com.example.APIManagerService.entity.Transactions;
import com.example.APIManagerService.service.ExpenseService;
import com.example.APIManagerService.service.TransactionService;
import com.example.APIManagerService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final ExpenseService expenseService;

    @Autowired
    public TransactionController(TransactionService transactionService, ExpenseService expenseService) {
        this.transactionService = transactionService;
        this.expenseService = expenseService;
    }

    @GetMapping()
    public String getTransactionsPage(@CookieValue("active-user-id") Long userId, Model model) {
        List<Transactions> transactions = transactionService.getAllTransactions(userId);
        for (Transactions transaction : transactions) {
            transaction.setExpense(expenseService.getExpenseById(transaction.getExpenseId()));
        }
        model.addAttribute("transactions", transactions);
        model.addAttribute("newTransaction", new TransactionDTO());
        return "transaction";
    }

    @PostMapping
    public String createTransaction(@CookieValue("active-user-id") Long userId,
                                    @ModelAttribute TransactionDTO transaction,
                                    RedirectAttributes redirectAttributes) {
        try {
            transaction.setSenderId(userId);
            transactionService.createTransaction(transaction);
            redirectAttributes.addFlashAttribute("successMessage", "Transaction created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create transaction: " + e.getMessage());
        }
        return "redirect:/transaction";
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
        return "redirect:/transaction";
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
        return "redirect:/transaction";
    }
}
