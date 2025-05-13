package com.example.MoneyManagementService.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String date;


    public Expenses(Long userId, Double amount, Long categoryId, String notes, String date) {
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.notes = notes;
        this.date = date;
    }


}
