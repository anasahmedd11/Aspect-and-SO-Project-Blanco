package com.example.DatabaseService.service;

import com.example.DatabaseService.DTO.CreateCategoryDTO;
import com.example.DatabaseService.entity.Categories;
import com.example.DatabaseService.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Categories getCategoryById(Long id) {
        return categoriesRepository.findById(id).orElse(null);
    }

    public Categories createCategory(CreateCategoryDTO createCategoryDTO) {
        Categories category = new Categories();
        category.setName(createCategoryDTO.getName());
        return categoriesRepository.save(category);
    }

    public Categories updateCategory(Long id, CreateCategoryDTO createCategoryDTO) {
        Categories category = categoriesRepository.findById(id).orElse(null);
        if (category != null) {
            category.setName(createCategoryDTO.getName());
            return categoriesRepository.save(category);
        }
        return null;
    }

    public void deleteCategory(Long id) {
        categoriesRepository.deleteById(id);
    }



}
