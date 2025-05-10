package com.example.DatabaseService.service;

import com.example.DatabaseService.entity.Categories;
import com.example.DatabaseService.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    // Add methods to interact with the CategoriesRepository
    public void addCategory(Categories category) {
        categoriesRepository.save(category);
    }

    public Categories getCategoryById(Long id) {
        return categoriesRepository.findById(id).orElse(null);
    }

    public void updateCategory(Categories category) {
        categoriesRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoriesRepository.deleteById(id);
    }


}
