package com.example.APIManagerService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiresAt;
    private Categories category;

    public Budgets(double currentAmount, double limitAmount, Date createdAt, Date expiresAt, Categories category) {
        this.currentAmount = currentAmount;
        this.limitAmount = limitAmount;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.category = category;
    }
}
