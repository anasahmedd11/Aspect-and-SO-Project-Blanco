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
    private Long senderId;
    private Long receiverId;
    private Long expenseId;
    private Expenses expense;

    public Transactions(Long senderId, Long receiverId, Long expenseId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.expenseId = expenseId;
    }

    public Transactions(Long senderId, Long receiverId, Expenses expense) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.expense = expense;
    }
}