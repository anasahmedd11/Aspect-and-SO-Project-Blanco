package com.example.DatabaseService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "budgets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Budgets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "current_amount", nullable = false)
    private int currentAmount;

    @Column(name = "limit_amount", nullable = false)
    private int limitAmount;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "expires_at", nullable = false)
    private Date expiresAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    public Budgets(int currentAmount, int limitAmount, Date createdAt, Date expiresAt, Users user, Categories category) {
        this.currentAmount = currentAmount;
        this.limitAmount = limitAmount;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
        this.category = category;
    }
}
