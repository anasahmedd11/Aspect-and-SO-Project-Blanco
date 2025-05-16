package com.example.APIManagerService.controller;


import com.example.APIManagerService.config.RestTemplateConfig;
import com.example.APIManagerService.entity.Categories;
import com.example.APIManagerService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String viewCategories(Model model) {
        List<Categories> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Categories getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    @ResponseBody
    public void createCategory(@RequestBody Categories category) {
        categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public void updateCategory(@PathVariable Long id, @RequestBody Categories category) {
        categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
