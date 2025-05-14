package com.example.DatabaseService.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBudgetDTO {
    @PositiveOrZero(message = "Current amount must be positive")
    private double currentAmount;

    @PositiveOrZero(message = "Limit amount must be positive")
    private double limitAmount;

    @FutureOrPresent(message = "Created at must be in the future or present")
    private Date createdAt;

    @Future(message = "Expires at must be in the future")
    private Date expiresAt;

    @NotNull
    private Long userId;

    @NotNull
    private Long categoryId;
}