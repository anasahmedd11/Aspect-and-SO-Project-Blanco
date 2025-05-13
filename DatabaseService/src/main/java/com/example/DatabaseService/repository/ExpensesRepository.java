package com.example.DatabaseService.repository;

import com.example.DatabaseService.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by user_id ID or date range
}
