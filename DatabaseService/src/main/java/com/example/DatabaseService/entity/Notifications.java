package com.example.DatabaseService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "notifications")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notifications{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "msg")
    private String msg;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private String Status;
    @Column(name = "sent_at")
    private Date sent_At;

    public Notifications(Users user, String msg, String type, String Status, Date sent_At) {
        this.user = user;
        this.msg = msg;
        this.type = type;
        this.Status = Status;
        this.sent_At = sent_At;
    }

}
