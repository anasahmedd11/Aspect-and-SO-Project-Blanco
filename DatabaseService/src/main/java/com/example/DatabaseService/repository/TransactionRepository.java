package com.example.DatabaseService.repository;

import com.example.DatabaseService.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByUserId(Long userId);

}