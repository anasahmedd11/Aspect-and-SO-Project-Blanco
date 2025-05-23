package com.example.MoneyManagementService.entity;


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
    private Double amount;
    private Long categoryId;
    private String categoryName;
    private String notes;
    private Date date;


    public Expenses(Double amount, String categoryName, String notes, Date date) {
        this.amount = amount;
        this.categoryName = categoryName;
        this.notes = notes;
        this.date = date;
    }

    public Expenses(Double amount, Long categoryId, String notes, Date date) {
        this.amount = amount;
        this.categoryId = categoryId;
        this.notes = notes;
        this.date = date;
    }
}
