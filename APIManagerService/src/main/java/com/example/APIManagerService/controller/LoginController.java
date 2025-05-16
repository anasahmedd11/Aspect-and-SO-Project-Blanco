package com.example.APIManagerService.controller;

import com.example.APIManagerService.DTO.Authentication.LoginRequestDTO;
import com.example.APIManagerService.DTO.Authentication.LoginResponseDTO;
import com.example.APIManagerService.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/Authentication/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new LoginRequestDTO());
        return "login";
    }

    @RequestMapping(value = "/Authentication/loginForm", method = RequestMethod.POST)
    @PostMapping("/Authentication/loginForm")
    public String loginForm(Model model, LoginRequestDTO user, RedirectAttributes redirectAttributes) {
        try {
            ResponseEntity<LoginResponseDTO> response = authenticationService.login(user);
            redirectAttributes.addFlashAttribute("message", "Logged in successfully!");
            return "redirect:/home";
        }
        catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("message", "Login failed! Please try again.");
            redirectAttributes.addFlashAttribute("user", new LoginRequestDTO());
            return "redirect:/Authentication/login";
        }
    }
}