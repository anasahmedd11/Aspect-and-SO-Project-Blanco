package com.example.DatabaseService.controller;

import com.example.DatabaseService.DTO.CreateBudgetDTO;
import com.example.DatabaseService.DTO.UpdateBudgetDTO;
import com.example.DatabaseService.entity.Budgets;
import com.example.DatabaseService.service.BudgetsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/db-service/budgets")
public class BudgetsController {
    @Autowired
    private BudgetsService budgetsService;


    @GetMapping("/user/{id}")
    public ResponseEntity<List<Budgets>> getAllUserBudgets(@PathVariable Long id) {
        List<Budgets> budgets = budgetsService.getAllUserBudgets(id);
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budgets> getBudgetById(@PathVariable Long id) {
        Optional<Budgets> budgetOptional = budgetsService.getBudgetById(id);

        if (budgetOptional.isPresent()) {
            Budgets user = budgetOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Budgets>> getBudgetsByCategoryId(@PathVariable Long id) {
        List<Budgets> budgets = budgetsService.getBudgetsByCategoryId(id);
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Budgets> createBudget(@Valid @RequestBody CreateBudgetDTO budgetDTO) {
        Budgets newBudget = budgetsService.createBudget(budgetDTO);
        return new ResponseEntity<>(newBudget, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Budgets> updateBudget(@PathVariable Long id, @Valid @RequestBody UpdateBudgetDTO updateBudgetDTO) {
        try {
            Budgets updatedBudget = budgetsService.updateBudget(id, updateBudgetDTO);
            return new ResponseEntity<>(updatedBudget, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        try {
            budgetsService.deleteBudget(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}