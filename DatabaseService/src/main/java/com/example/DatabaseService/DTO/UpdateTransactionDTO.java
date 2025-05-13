package com.example.DatabaseService.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransactionDTO {
    @NotNull
    private Long receiverId;

    @NotNull
    private Long categoryId;
}