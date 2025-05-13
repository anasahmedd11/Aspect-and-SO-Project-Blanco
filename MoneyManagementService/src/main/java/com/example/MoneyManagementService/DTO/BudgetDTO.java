package com.example.MoneyManagementService.DTO;


import com.example.MoneyManagementService.entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BudgetDTO {
    private int currentAmount;
    private int limitAmount;
    private Date createdAt;
    private Date expiresAt;
    private long user_id;
    private Categories category;


}
