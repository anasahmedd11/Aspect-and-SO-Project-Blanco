package com.example.NotificationService.Entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Notifications {

    private Long id;
    private Long userId;
    private String msg;
    private String type;
    private String Status;
    private Date sent_At;

    public Notifications(Long userId, String msg, String type, String Status, Date sent_At) {
        this.userId = userId;
        this.msg = msg;
        this.type = type;
        this.Status = Status;
        this.sent_At = sent_At;
    }

}
