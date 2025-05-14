package com.example.NotificationService.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsDTO {

    private Long user_id;
    private String msg;
    private String type;
    private String Status;
    private Date sent_At;

}
