package com.example.APIManagerService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @GetMapping("/home")
    public String homePage(Model model) {
        //model.addAttribute("message", "Welcome to Thymeleaf!");
        return "home";
    }

}
