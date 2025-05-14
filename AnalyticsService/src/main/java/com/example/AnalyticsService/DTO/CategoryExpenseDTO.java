package com.example.AnalyticsService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryExpenseDTO {
    private String categoryName;
    private Double totalAmount;
}
