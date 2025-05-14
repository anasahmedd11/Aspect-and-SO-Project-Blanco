package com.example.AnalyticsService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expenses {

    private Long id;
    private Long userId;
    private Double amount;
    private Long categoryId;
    private String notes;
    private Date date;

    public Expenses(Long userId, Double amount, Long categoryId, String notes, Date date) {
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.notes = notes;
        this.date = date;
    }

}
