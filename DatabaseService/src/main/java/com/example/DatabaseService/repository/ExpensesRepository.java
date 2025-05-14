package com.example.DatabaseService.repository;

import com.example.DatabaseService.entity.Expenses;
import com.example.DatabaseService.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    List<Expenses> findByUserId(Long userId);

    List<Expenses> findByUserIdAndDate(Long userId, Date endDate);

    @Query("SELECT e FROM Expenses e WHERE e.date >= :startDate and e.user.id == :userId")
    List<Expenses> findUserDailyExpenses(@Param("startDate") Date startDate, @Param("userId") Long userId);

}
