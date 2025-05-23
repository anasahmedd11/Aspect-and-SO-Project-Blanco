package com.example.DatabaseService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GetTransactionDTO {

    private Long senderId;
    private Long receiverId;
    private Long expenseId;
}
