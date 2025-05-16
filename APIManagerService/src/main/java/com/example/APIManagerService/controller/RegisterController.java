package com.example.APIManagerService.controller;

import com.example.APIManagerService.DTO.Authentication.LoginRequestDTO;
import com.example.APIManagerService.DTO.Authentication.RegisterRequestDTO;
import com.example.APIManagerService.DTO.Authentication.RegisterResponseDTO;
import com.example.APIManagerService.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegisterController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/Authentication/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new RegisterRequestDTO());
        return "register";
    }

    @RequestMapping(value = "/Authentication/registerForm", method = RequestMethod.POST)
    @PostMapping("/Authentication/registerForm")
    public String registerForm(Model model, RegisterRequestDTO user, RedirectAttributes redirectAttributes) {
        try {
            ResponseEntity<RegisterResponseDTO> response = authenticationService.register(user);
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Registration successful! Please log in."
            );
            redirectAttributes.addFlashAttribute("user", new LoginRequestDTO());
            return "redirect:/Authentication/login";
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                redirectAttributes.addFlashAttribute(
                        "message",
                        "User with this email already exists!"
                );
            } else {
                redirectAttributes.addFlashAttribute(
                        "message",
                        "Registration failed! Please try again."
                );
            }

            redirectAttributes.addFlashAttribute("user", new RegisterRequestDTO());
            return "redirect:/Authentication/register";
        }
    }
}