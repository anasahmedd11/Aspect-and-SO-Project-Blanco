package com.example.MoneyManagementService.service;

import com.example.MoneyManagementService.DTO.CategoriesDTO;
import com.example.MoneyManagementService.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoriesService {
    private final RestTemplate restTemplate;
    private final String databaseServiceUrl = "http://Blanco-Database-Service:8081/db-service/categories";

    @Autowired
    public CategoriesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Categories> getAllCategories() {
        Categories[] categories = restTemplate.getForObject(
                databaseServiceUrl,
                Categories[].class
        );
        return Arrays.asList(categories);
    }

    public Categories getCategoryById(Long id) {
        return restTemplate.getForObject(
                databaseServiceUrl + "/" + id,
                Categories.class
        );
    }

    public Categories createCategory(CategoriesDTO categoryDTO) {
        return restTemplate.postForObject(
                databaseServiceUrl,
                categoryDTO,
                Categories.class
        );
    }

    public Categories updateCategory(Long id, CategoriesDTO categoryDTO) {
        ResponseEntity<Categories> response = restTemplate.exchange(
                databaseServiceUrl + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(categoryDTO),
                Categories.class
        );
        return response.getBody();
    }

    public void deleteCategory(Long id) {
        restTemplate.delete(databaseServiceUrl + "/" + id);
    }

}