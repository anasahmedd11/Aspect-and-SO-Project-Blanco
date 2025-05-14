package com.example.AnalyticsService.controller;

import com.example.AnalyticsService.DTO.BarChartDataDTO;
import com.example.AnalyticsService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics-service/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/bar-chart-data")
    public BarChartDataDTO getBarChartData() {
        return categoryService.getBarChartData();
    }
}
