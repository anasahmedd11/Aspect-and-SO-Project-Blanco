package com.example.DatabaseService.service;

import com.example.DatabaseService.DTO.CreateBudgetDTO;
import com.example.DatabaseService.DTO.UpdateBudgetDTO;
import com.example.DatabaseService.entity.Budgets;
import com.example.DatabaseService.entity.Categories;
import com.example.DatabaseService.entity.Users;
import com.example.DatabaseService.repository.BudgetsRepository;
import com.example.DatabaseService.repository.CategoriesRepository;
import com.example.DatabaseService.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.DatabaseService.service.NullPropertyNameHelper.getNullPropertyNames;

@Service
public class BudgetsService {

    @Autowired
    private BudgetsRepository budgetsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private UsersRepository usersRepository;


    public Budgets createBudget(CreateBudgetDTO budgetDTO) {
        Long userId = budgetDTO.getUserId();
        Users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Long categoryId = budgetDTO.getCategoryId();
        Categories existingCategory = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + categoryId));

        Budgets budget = new Budgets(
                budgetDTO.getCurrentAmount(),
                budgetDTO.getLimitAmount(),
                budgetDTO.getCreatedAt(),
                budgetDTO.getExpiresAt(),
                existingUser,
                existingCategory
        );
        return budgetsRepository.save(budget);
    }

    public List<Budgets> getAllUserBudgets(Long userId) {
        return budgetsRepository.findByUserId(userId);
    }

    public Optional<Budgets> getBudgetById(Long id) {
        return budgetsRepository.findById(id);
    }

    public Budgets updateBudget(Long id, UpdateBudgetDTO budgetDTO) {
        Budgets existingBudget = budgetsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found with id: " + id));

        BeanUtils.copyProperties(budgetDTO, existingBudget, getNullPropertyNames(budgetDTO));
        return budgetsRepository.save(existingBudget);
    }

    public void deleteBudget(Long id) {
        budgetsRepository.deleteById(id);
    }
}
