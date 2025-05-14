package com.example.AnalyticsService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDTO {

    private double currentAmount;
    private double limitAmount;
    private String createdAt;
    private String expiresAt;
    private Long user_id;
    private Long category_id;


}
