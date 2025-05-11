package com.example.DatabaseService.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNotificationDTO {

    @NotBlank(message = "Message is required")
    private String msg;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Status is required")
    private String status;

    @PastOrPresent(message = "Sent time must be in the present or the past")
    private Date sent_At;

}
