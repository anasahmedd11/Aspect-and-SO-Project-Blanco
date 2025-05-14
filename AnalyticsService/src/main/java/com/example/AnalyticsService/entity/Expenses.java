package com.example.AnalyticsService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class Expenses {

    private Long id;
    private Long user_id;
    private Double amount;
    private Categories category;
    private String notes;
    private Date date;

}
