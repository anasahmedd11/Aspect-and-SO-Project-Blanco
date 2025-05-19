package com.example.DatabaseService.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionDTO {
    @NotNull
    private Long receiverId;

    @NotNull
    private Long senderId;

    @NotNull
    private Long expenseId;

}