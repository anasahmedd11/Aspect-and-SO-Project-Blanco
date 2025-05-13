package com.example.MoneyManagementService.service;

import com.example.MoneyManagementService.DTO.CategoriesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoriesService {
    private final RestTemplate restTemplate;
    private final String databaseServiceUrl = "/db-service/";

    @Autowired
    public CategoriesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CategoriesDTO createCategory(CategoriesDTO categoryDTO) {
        return restTemplate.postForObject(
                databaseServiceUrl + "/categories/",
                categoryDTO,
                CategoriesDTO.class
        );
    }

    public void deleteCategory(Long id) {
        restTemplate.delete(databaseServiceUrl + "/categories/" + id);
    }

    public List<CategoriesDTO> getAllCategories() {
        CategoriesDTO[] categories = restTemplate.getForObject(
                databaseServiceUrl + "/categories/",
                CategoriesDTO[].class
        );
        return Arrays.asList(categories);
    }
}