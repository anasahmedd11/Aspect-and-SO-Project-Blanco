package com.example.NotificationService.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notifications {

    private Long id;
    private Long user_id;
    private String msg;
    private String type;
    private String Status;
    private Date sent_At;

    public Notifications(Long user_id, String msg, String type, String Status, Date sent_At) {
        this.user_id = user_id;
        this.msg = msg;
        this.type = type;
        this.Status = Status;
        this.sent_At = sent_At;
    }

}
