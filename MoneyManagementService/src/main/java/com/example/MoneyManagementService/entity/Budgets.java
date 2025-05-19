package com.example.MoneyManagementService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiresAt;
    private Long user_id;
    private Long category_id;

    public Budgets(double currentAmount, double limitAmount, Date createdAt, Date expiresAt, Long user, Long category) {
        this.currentAmount = currentAmount;
        this.limitAmount = limitAmount;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user_id = user;
        this.category_id = category;
    }

}
