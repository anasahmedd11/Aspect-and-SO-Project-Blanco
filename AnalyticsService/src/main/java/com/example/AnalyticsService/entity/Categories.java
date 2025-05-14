package com.example.AnalyticsService.entity;

import jakarta.persistence.*;

import java.util.List;

public class Categories {

    private Long id;
    private String name;
    private List<Expenses> expenses;
    private List<Budgets> budgets;

}
