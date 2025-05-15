package com.example.AnalyticsService.service;

import com.example.AnalyticsService.DTO.BarChartDataDTO;
import com.example.AnalyticsService.DTO.CategoryExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final RestTemplate restTemplate;
    private final String databaseServiceUrl = "http://Blanco-Database-Service:8081/db-service/categories";

    @Autowired
    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BarChartDataDTO getBarChartData(Long id) {
        String url = databaseServiceUrl + "/user/" + id + "/bar-chart-data";
        CategoryExpenseDTO[] data = restTemplate.getForObject(url, CategoryExpenseDTO[].class);

        List<String> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        if (data != null) {
            for (CategoryExpenseDTO dto : data) {
                xValues.add(dto.getCategoryName());
                yValues.add(dto.getTotalAmount());
            }
        }
        return new BarChartDataDTO(xValues, yValues);
    }
}
