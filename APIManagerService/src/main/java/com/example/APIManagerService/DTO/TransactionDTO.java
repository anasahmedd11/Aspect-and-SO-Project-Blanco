package com.example.APIManagerService.DTO;

import com.example.APIManagerService.entity.Expenses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Double amount;
    private String notes;
    private String receiverEmail;
    private Long senderId;
}
