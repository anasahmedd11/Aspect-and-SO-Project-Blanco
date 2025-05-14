package com.example.MoneyManagementService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Budgets {

    private Long id;
    private double currentAmount;
    private double limitAmount;
    private Date createdAt;
    private Date expiresAt;
    private Long user_id;
    private Categories category;

    public Budgets(double currentAmount, double limitAmount, Date createdAt, Date expiresAt, long user, Categories category) {
        this.currentAmount = currentAmount;
        this.limitAmount = limitAmount;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user_id = user;
        this.category = category;
    }

}
