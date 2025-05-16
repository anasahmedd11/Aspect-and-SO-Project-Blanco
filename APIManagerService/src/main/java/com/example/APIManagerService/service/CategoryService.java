package com.example.APIManagerService.service;

import com.example.APIManagerService.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    private final RestTemplate restTemplate;
    private String MoneyManagURL = "http://localhost:8083/money-management/categories";

    @Autowired
    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Categories> getAllCategories() {
        ResponseEntity<Categories[]> response = restTemplate.getForEntity(MoneyManagURL, Categories[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
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

