package com.example.DatabaseService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Setter
@Getter
public class Notifications{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)

    private Users user;
    private long user_id;
    private String msg;
    private String type;
    private String Status;
    private Date sent_At;

    public Notifications() {
    }
    public Notifications(long user_id, String msg, String type, String Status, Date sent_At) {
        this.user_id = user_id;
        this.msg = msg;
        this.type = type;
        this.Status = Status;
        this.sent_At = sent_At;
    }

}
