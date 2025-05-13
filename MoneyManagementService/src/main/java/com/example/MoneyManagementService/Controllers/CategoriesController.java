package com.example.MoneyManagementService.Controllers;

import com.example.MoneyManagementService.DTO.CategoriesDTO;
import com.example.MoneyManagementService.entity.Categories;
import com.example.MoneyManagementService.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/money-management-service/categories")
public class CategoriesController {
    private final CategoriesService categoryService;

    @Autowired
    public CategoriesController(CategoriesService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        Categories category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categories> createCategory(@RequestBody CategoriesDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable Long id, @RequestBody CategoriesDTO categoryDTO) {
        Categories updatedCategory = categoryService.updateCategory(id, categoryDTO);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

}