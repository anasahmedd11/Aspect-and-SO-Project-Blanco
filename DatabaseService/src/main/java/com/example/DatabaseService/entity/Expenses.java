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

    // user object from table users


    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories Category;

    @Column(name = "notes")
    private String notes;


    @Column(name = "date")
    private Date date;



}
