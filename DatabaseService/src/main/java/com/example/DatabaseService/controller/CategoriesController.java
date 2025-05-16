package com.example.DatabaseService.controller;

import com.example.DatabaseService.DTO.CreateCategoryDTO;
import com.example.DatabaseService.entity.Categories;
import com.example.DatabaseService.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db-service/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    // Endpoint to get all categories
    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories() {
        List<Categories> categories = categoriesService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Endpoint to get a category by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable("id") Long id) {
        Categories category = categoriesService.getCategoryById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // Endpoint to create a new category
    @PostMapping
    public ResponseEntity<Categories> createCategory(@RequestBody CreateCategoryDTO createCategoryDTO) {
        Categories category = categoriesService.createCategory(createCategoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // Endpoint to update an existing category
    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable("id") Long id, @RequestBody CreateCategoryDTO createCategoryDTO) {
        Categories updatedCategory = categoriesService.updateCategory(id, createCategoryDTO);
        if (updatedCategory != null) {
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // Endpoint to delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        Categories category = categoriesService.getCategoryById(id);
        if (category != null) {
            categoriesService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
