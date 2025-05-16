package com.example.APIManagerService.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoriesController {

    @GetMapping("/categories")
    public String budget() {
        return "categories";
    }
}
