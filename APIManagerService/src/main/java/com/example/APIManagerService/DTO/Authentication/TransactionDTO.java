package com.example.APIManagerService.DTO.Authentication;


import lombok.*;

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
