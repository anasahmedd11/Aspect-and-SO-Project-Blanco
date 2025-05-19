package com.example.APIManagerService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBudgetDTO {
    private double currentAmount;
    private double limitAmount;
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiresAt;
    private Long categoryId;
    private Long userId;
}
