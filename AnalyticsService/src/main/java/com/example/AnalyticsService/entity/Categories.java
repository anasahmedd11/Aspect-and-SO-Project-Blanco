package com.example.AnalyticsService.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categories {

    private Long id;
    private String name;
    private List<Expenses> expenses;
    private List<Budgets> budgets;

}
