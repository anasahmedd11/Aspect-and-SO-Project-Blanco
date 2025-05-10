package com.example.DatabaseService.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class NotificationsDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Message is required")
    private String msg;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Sent date is required")
    private Date sent_At;

    public NotificationsDTO() {
    }

    public NotificationsDTO(Long userId, String msg, String type, String status, Date sentAt) {
        this.userId = userId;
        this.msg = msg;
        this.type = type;
        this.status = status;
        this.sent_At = sentAt;
    }
}