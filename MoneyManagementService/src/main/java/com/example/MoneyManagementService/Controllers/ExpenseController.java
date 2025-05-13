package com.example.MoneyManagementService.Controllers;
import com.example.MoneyManagementService.DTO.ExpenseDTO;
import com.example.MoneyManagementService.entity.Expenses;
import com.example.MoneyManagementService.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/money-management/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expenses>> getAllUserExpenses(@PathVariable Long userId) {
        List<Expenses> expenses = expenseService.getAllUserExpenses(userId);
        if (expenses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expenses> getExpenseById(@PathVariable Long id) {
        Optional<Expenses> expense = expenseService.getExpenseById(id);
        return expense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Expenses> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        Expenses createdExpense = expenseService.createExpense(expenseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expenses> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO updateExpenseDTO) {
        Expenses updatedExpense = expenseService.updateExpense(id, updateExpenseDTO);
        if (updatedExpense != null) {
            return ResponseEntity.ok(updatedExpense);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        if (expenseService.deleteExpense(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<List<Expenses>> getExpensesByCategory(@PathVariable Long userId, @PathVariable Long categoryId) {
        List<Expenses> expenses = expenseService.getExpensesByCategory(userId, categoryId);
        if (expenses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<Expenses>> getExpensesByDateRange(@PathVariable Long userId,
                                                                 @RequestParam String startDate,
                                                                 @RequestParam String endDate) {
        List<Expenses> expenses = expenseService.getExpensesByDateRange(userId, startDate, endDate);
        if (expenses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expenses);
    }
}
