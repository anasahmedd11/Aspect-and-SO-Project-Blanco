package com.example.APIManagerService.DTO.Authentication;

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
public class BudgetDTO {

    private double currentAmount;
    private double limitAmount;
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiresAt;
    private Long userId;
    private Long categoryId;




}
