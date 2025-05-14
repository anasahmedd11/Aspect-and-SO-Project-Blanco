package com.example.AnalyticsService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class Budgets {
    private Long id;

    private double currentAmount;

    private double limitAmount;

    private Date createdAt;

    private Date expiresAt;

    private Long userId;

    private Long categoryId;

}
