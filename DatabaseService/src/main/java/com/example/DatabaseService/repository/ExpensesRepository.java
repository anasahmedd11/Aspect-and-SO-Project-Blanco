package com.example.DatabaseService.repository;

import com.example.DatabaseService.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by user_id ID or date range

    List<Expenses> findByUserId(Long userId);

    List<Expenses> findByUserIdAndDate(Long userId, Date endDate);


}
