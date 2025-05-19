package com.example.DatabaseService.DTO;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiresAt;

    @NotNull
    private Long userId;

    @NotNull
    private Long categoryId;
}