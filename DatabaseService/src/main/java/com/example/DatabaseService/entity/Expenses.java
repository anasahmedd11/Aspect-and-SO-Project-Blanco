package com.example.DatabaseService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // user_id object from table users
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;


    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    @Column(name = "notes",nullable = false)
    private String notes;


    @Column(name = "date",nullable = false)
    private Date date;


    public Expenses(Users user, Double amount, Categories category, String notes, Date date) {
        this.user = user;
        this.amount = amount;
        this.category = category;
        this.notes = notes;
        this.date = date;
    }



}
