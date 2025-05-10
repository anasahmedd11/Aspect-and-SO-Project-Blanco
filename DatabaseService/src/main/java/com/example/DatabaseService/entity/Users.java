package com.example.DatabaseService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Expenses> expenses = new ArrayList<>();

//    public void addExpense(Expenses expense) {
//        expenses.add(expense);
//        expense.setUser(this);
//    }
//
//    public void removeExpense(Expenses expense) {
//        expenses.remove(expense);
//        expense.setUser(null);
//    }

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Notifications> notifications = new ArrayList<>();

//    public void addNotification(Notifications notification) {
//        notifications.add(notification);
//        notification.setUser(this);
//    }
//
//    public void removeNotification(Notifications notification) {
//        notifications.remove(notification);
//        notification.setUser(null);
//    }

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Transactions> transactions = new ArrayList<>();

//    public void addTransaction(Transactions transaction) {
//        transactions.add(transaction);
//        transaction.setUser(this);
//    }
//
//    public void removeTransaction(Transactions transaction) {
//        transactions.remove(transaction);
//        transaction.setUser(null);
//    }

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Budgets> budgets = new ArrayList<>();

//    public void addBudget(Budgets budget) {
//        budgets.add(budget);
//        budget.setUser(this);
//    }
//
//    public void removeBudget(Budgets budget) {
//        budgets.remove(budget);
//        budget.setUser(null);
//    }

}