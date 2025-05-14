package com.example.DatabaseService.controller;

import com.example.DatabaseService.DTO.CategoryExpenseDTO;
import com.example.DatabaseService.DTO.CreateExpenseDTO;
import com.example.DatabaseService.DTO.UpdateExpenseDTO;
import com.example.DatabaseService.entity.Expenses;
import com.example.DatabaseService.service.ExpensesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/db-service/expenses")
public class ExpensesController {

    private final ExpensesService expensesService;

    @Autowired
    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }


    @GetMapping
    public List<Expenses> getAllExpenses() {
        return expensesService.getAllExpenses();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Expenses> getExpenseById(@PathVariable Long id) {
        Expenses expense = expensesService.getExpenseById(id);
        if (expense != null) {
            return ResponseEntity.ok(expense);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/expenses-by-category")
    public List<CategoryExpenseDTO> getExpensesByCategory() {
        return expensesService.getTotalExpenseAmountByCategory();
    }

    @PostMapping
    public ResponseEntity<Expenses> createExpense(@Valid @RequestBody CreateExpenseDTO createExpenseDTO) {
        Expenses createdExpense = expensesService.createExpense(createExpenseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expenses> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody UpdateExpenseDTO updateExpenseDTO) {
        Expenses updatedExpense = expensesService.updateExpense(id, updateExpenseDTO);
        if (updatedExpense != null) {
            return ResponseEntity.ok(updatedExpense);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expensesService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expenses>> getAllUserExpenses(@PathVariable Long userId) {
        List<Expenses> expenses = expensesService.getAllUserExpenses(userId);
        if (expenses != null && !expenses.isEmpty()) {
            return ResponseEntity.ok(expenses);
        }
        return ResponseEntity.notFound().build();
    }

    // get expenses by date
    @GetMapping("/user/{userId}/date/{date}")
    public ResponseEntity<List<Expenses>> getExpensesByDate(@PathVariable Long userId, @PathVariable Date date) {
        List<Expenses> expenses = expensesService.getExpensesByDate(userId, date);
        if (expenses != null && !expenses.isEmpty()) {
            return ResponseEntity.ok(expenses);
        }
        return ResponseEntity.notFound().build();
    }

}
