package com.example.MoneyManagementService.DTO;

import com.example.MoneyManagementService.entity.Expenses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long sender_id;
    private Long receiver_id;
    private Expenses expenses;
}
