package com.example.APIManagerService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Budgets {

    private Long id;
    private Long userId;
    private double currentAmount;
    private double limitAmount;
    private Date createdAt;
    private Date expiresAt;
    private Long categoryId;

    public Budgets(double currentAmount, double limitAmount, Date createdAt, Date expiresAt, Long userId, Long categoryId) {
        this.currentAmount = currentAmount;
        this.limitAmount = limitAmount;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.userId = userId;
        this.categoryId = categoryId;
    }


}
