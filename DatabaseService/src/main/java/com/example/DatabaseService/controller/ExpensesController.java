package com.example.DatabaseService.controller;

import com.example.DatabaseService.DTO.*;
import com.example.DatabaseService.entity.Expenses;
import com.example.DatabaseService.service.ExpensesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
    public List<CreateExpenseResponse> getAllExpenses() {
        return expensesService.getAllExpenses();
    }


    @GetMapping("/{id}")
    public ResponseEntity<GetExpenseDTO> getExpenseById(@PathVariable Long id) {
        Expenses expense = expensesService.getExpenseById(id);
        if (expense != null) {
            GetExpenseDTO getExpenseDTO = new GetExpenseDTO(
                    expense.getId(),
                    expense.getAmount(),
                    expense.getCategory().getName(),
                    expense.getNotes(),
                    expense.getDate()
            );
            return ResponseEntity.ok(getExpenseDTO);
        }
        return ResponseEntity.noContent().build();
    }

    //Generate Monthly Report
    @GetMapping("/monthly-report/user/{id}")
    public List<GetExpenseDTO> getMonthlyReport(@PathVariable Long id) {
        List<Expenses> expenses = expensesService.getUserMonthlyExpenses(id);
        List<GetExpenseDTO> getExpenseDTOs = new ArrayList<>();
        for (Expenses expense : expenses) {
            GetExpenseDTO getExpenseDTO = new GetExpenseDTO(
                    expense.getId(),
                    expense.getAmount(),
                    expense.getCategory().getName(),
                    expense.getNotes(),
                    expense.getDate()
            );
            getExpenseDTOs.add(getExpenseDTO);
        }
        return getExpenseDTOs;
    }

    @GetMapping("/user/{userId}/expenses-by-category")
    public List<CategoryExpenseDTO> getExpensesByCategory(@PathVariable Long userId) {
        return expensesService.getTotalExpenseAmountByCategory(userId);
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
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expensesService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GetExpenseDTO>> getAllUserExpenses(@PathVariable Long userId) {
        List<Expenses> expenses = expensesService.getAllUserExpenses(userId);
        if (expenses != null && !expenses.isEmpty()) {
            List<GetExpenseDTO> getExpenseDTOs = new ArrayList<>();
            for (Expenses expense : expenses) {
                GetExpenseDTO getExpenseDTO = new GetExpenseDTO(
                        expense.getId(),
                        expense.getAmount(),
                        expense.getCategory().getName(),
                        expense.getNotes(),
                        expense.getDate()
                );
                getExpenseDTOs.add(getExpenseDTO);
            }
            return ResponseEntity.ok(getExpenseDTOs);
        }
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }

    // get expenses by date
    @GetMapping("/user/{userId}/date/{date}")
    public ResponseEntity<List<GetExpenseDTO>> getExpensesByDate(@PathVariable Long userId, @PathVariable Date date) {
        List<Expenses> expenses = expensesService.getExpensesByDate(userId, date);
        if (expenses != null && !expenses.isEmpty()) {
            List<GetExpenseDTO> getExpenseDTOs = new ArrayList<>();
            for (Expenses expense : expenses) {
                GetExpenseDTO getExpenseDTO = new GetExpenseDTO(
                        expense.getId(),
                        expense.getAmount(),
                        expense.getCategory().getName(),
                        expense.getNotes(),
                        expense.getDate()
                );
                getExpenseDTOs.add(getExpenseDTO);
            }
            return ResponseEntity.ok(getExpenseDTOs);
        }
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }

}
