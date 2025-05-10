package com.example.DatabaseService.service;

import com.example.DatabaseService.entity.Expenses;
import com.example.DatabaseService.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesService {

    private final ExpensesRepository expensesRepository;

    @Autowired
    public ExpensesService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    // Add methods to interact with the ExpensesRepository
    public void addExpense(Expenses expense) {
        expensesRepository.save(expense);
    }

    public Expenses getExpenseById(Long id) {
        return expensesRepository.findById(id).orElse(null);
    }

    public void updateExpense(Expenses expense) {
        expensesRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expensesRepository.deleteById(id);
    }
}
