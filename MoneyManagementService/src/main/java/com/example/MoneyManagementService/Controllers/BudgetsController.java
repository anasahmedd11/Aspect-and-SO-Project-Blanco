package com.example.MoneyManagementService.Controllers;

import com.example.MoneyManagementService.DTO.BudgetDTO;
import com.example.MoneyManagementService.entity.Budgets;
import com.example.MoneyManagementService.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/money-management/budgets")
public class BudgetsController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetsController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Budgets>> getAllUserBudgets(@PathVariable Long userId) {
        List<Budgets> budgets = budgetService.getAllUserBudgets(userId);
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budgets> getBudgetById(@PathVariable Long id) {
        Optional<Budgets> budget = budgetService.getBudgetById(id);
        return budget.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.ok((Budgets) Collections.emptyList()));
    }

    @PostMapping
    public ResponseEntity<Budgets> createBudget(@RequestBody BudgetDTO budgetDTO) {
        Budgets createdBudget = budgetService.createBudget(budgetDTO);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budgets> updateBudget(@PathVariable Long id, @RequestBody BudgetDTO budgetDTO) {
        Budgets updatedBudgets = budgetService.updateBudget(id, budgetDTO);
        if (updatedBudgets == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(updatedBudgets, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        boolean deleted = budgetService.deleteBudget(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
