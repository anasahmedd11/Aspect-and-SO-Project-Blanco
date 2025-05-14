package com.example.MoneyManagementService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {


    private Long userId;
    private Double amount;
    private Long categoryId;
    private String notes;
    private Date date;
}
