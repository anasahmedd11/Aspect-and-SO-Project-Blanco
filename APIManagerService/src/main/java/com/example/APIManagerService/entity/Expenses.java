package com.example.APIManagerService.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expenses {
    private Long id;
    private Long userId;
    private Double amount;
    private String categoryName;
    private Long categoryId;
    private String notes;
    private Date date;

    public Expenses(Long userId, Double amount, String categoryName, String notes, Date date) {
        this.userId = userId;
        this.amount = amount;
        this.categoryName = categoryName;
        this.notes = notes;
        this.date = date;
    }

    public Expenses(Long userId, Double amount, Long categoryId, String notes, Date date) {
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.notes = notes;
        this.date = date;
    }
}
