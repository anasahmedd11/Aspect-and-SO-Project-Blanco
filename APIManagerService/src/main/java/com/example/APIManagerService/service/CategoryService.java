package com.example.APIManagerService.service;

import com.example.APIManagerService.entity.Categories;
import com.example.APIManagerService.entity.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CategoryService {

    private final RestTemplate restTemplate;
    private String MoneyManagURL = "http://Blanco-Money-Management-Service:8083/money-management/categories";
    private String AnalyticsURL = "http://Blanco-Analytics-Service:8084/analytics-service/categories";

    @Autowired
    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, List<?>> getExpenseBarChartDate(Long id) {
        String url = AnalyticsURL + "/bar-chart-data/user/" + id;
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }

    public List<Categories> getAllCategories() {
        Categories[] response = restTemplate.getForObject(MoneyManagURL, Categories[].class);
        return Arrays.asList(response);
    }

    public Categories getCategoryById(Long id) {
        return restTemplate.getForObject(MoneyManagURL + "/" + id, Categories.class);
    }

    public void createCategory(Categories Categories) {
        restTemplate.postForEntity(MoneyManagURL, Categories, Void.class);
    }

    public void updateCategory(Long id, Categories Categories) {
        HttpEntity<Categories> requestEntity = new HttpEntity<>(Categories);
        restTemplate.exchange(MoneyManagURL + "/" + id, HttpMethod.PUT, requestEntity, Void.class);
    }

    public void deleteCategory(Long id) {
        restTemplate.delete(MoneyManagURL + "/" + id);
    }
}

