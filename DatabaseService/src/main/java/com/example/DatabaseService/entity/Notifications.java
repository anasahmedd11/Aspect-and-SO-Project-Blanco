package com.example.DatabaseService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notifications{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private String msg;
    private String type;
    private String Status;
    private Date sent_At;

    public Notifications(Users user, String msg, String type, String Status, Date sent_At) {
        this.user = user;
        this.msg = msg;
        this.type = type;
        this.Status = Status;
        this.sent_At = sent_At;
    }

}
