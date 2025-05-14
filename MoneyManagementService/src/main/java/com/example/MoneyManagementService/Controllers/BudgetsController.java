package com.example.MoneyManagementService.Controllers;

import com.example.MoneyManagementService.DTO.BudgetDTO;
import com.example.MoneyManagementService.entity.Budgets;
import com.example.MoneyManagementService.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/budgets")
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
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Budgets> createBudget(@RequestBody BudgetDTO budgetDTO) {
        Budgets createdBudget = budgetService.createBudget(budgetDTO);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Budgets>> updateBudget(@PathVariable Long id, @RequestBody BudgetDTO budgetDTO) {
        List<Budgets> updatedBudgets = budgetService.updateBudget(id, budgetDTO);
        if (updatedBudgets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedBudgets, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        boolean deleted = budgetService.deleteBudget(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
