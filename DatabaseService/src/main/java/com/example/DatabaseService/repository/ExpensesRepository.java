package com.example.DatabaseService.repository;

import com.example.DatabaseService.DTO.CategoryExpenseDTO;
import com.example.DatabaseService.entity.Expenses;
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

    @Query("SELECT e FROM  Expenses e WHERE e.date >= :startDate and e.user.id == :userId")
    List<Expenses> getUserExpenses(@Param("startDate") Date startDate, @Param("userId") Long userId);

    //top query from Mohamed Khaled
    @Query("SELECT e.category.name , SUM(e.amount) FROM Expenses e  GROUP BY e.category.name")
    List<CategoryExpenseDTO> findTotalExpenseAmountByCategory();

}
