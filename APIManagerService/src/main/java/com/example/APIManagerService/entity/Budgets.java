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
    private double amount;
    private Date startDate;
    private Date endDate;
    private Long categoryId;

    public Budgets(double amount, Date startDate, Date endDate, Long userId, Long categoryId) {
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.categoryId = categoryId;
    }


}
