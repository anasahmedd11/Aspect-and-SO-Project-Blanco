package com.example.DatabaseService.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class GetTransactionDTO {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private Long expenseId;

    public GetTransactionDTO(Long senderId, Long receiverId, Long expenseId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.expenseId = expenseId;
    }

}
