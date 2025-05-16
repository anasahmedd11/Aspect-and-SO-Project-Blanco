package com.example.APIManagerService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    private Long id;
    private Long sender_id;
    private Long receiver_id;
    private Expenses expenses;

    public Transactions(Long sender_id, Long receiver_id, Expenses expenses) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.expenses = expenses;
    }
}