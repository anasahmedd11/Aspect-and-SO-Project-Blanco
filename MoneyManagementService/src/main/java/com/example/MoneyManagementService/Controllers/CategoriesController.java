package com.example.MoneyManagementService.Controllers;

import com.example.MoneyManagementService.DTO.CategoriesDTO;
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

    @PostMapping
    public ResponseEntity<CategoriesDTO> createCategory(@RequestBody CategoriesDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriesDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}