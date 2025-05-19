package com.example.MoneyManagementService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
    private Long id;
    private Long expenseId;
    private Long senderId;
    private Long receiverId;

    Transactions(Long senderId, Long receiverId, Long expenseId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.expenseId = expenseId;
    }
}
