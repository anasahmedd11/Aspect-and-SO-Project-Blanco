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
    private Long user_id;
    private Double amount;
    private Categories category;
    private String notes;
    private Date date;


    public Expenses(Long userId, Double amount, Categories categoryId, String notes, Date date) {
        this.user_id = userId;
        this.amount = amount;
        this.category = categoryId;
        this.notes = notes;
        this.date = date;
    }


}
