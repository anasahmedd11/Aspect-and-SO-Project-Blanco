package com.example.DatabaseService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateExpenseResponse {
    private Long userId;
    private Double amount;
    private Long categoryId;
    private String notes;
    private Date date;
}
