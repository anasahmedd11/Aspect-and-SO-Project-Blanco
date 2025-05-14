package com.example.DatabaseService.repository;

import com.example.DatabaseService.entity.Budgets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetsRepository extends JpaRepository<Budgets, Long> {
    List<Budgets> findByUserId(Long userId);
    List<Budgets> findByCategoryId(Long categoryId);
}
