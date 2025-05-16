package com.example.APIManagerService.controller;

import com.example.APIManagerService.DTO.Authentication.LoginRequestDTO;
import com.example.APIManagerService.DTO.Authentication.LoginResponseDTO;
import com.example.APIManagerService.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String loginForm(Model model, LoginRequestDTO user,
                            RedirectAttributes redirectAttributes, HttpServletResponse response) {
        try {
            LoginResponseDTO loginResponse = authenticationService.login(user);

            Cookie cookie = new Cookie("blanco-jwt", loginResponse.getToken());
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);

            redirectAttributes.addFlashAttribute("message", "Logged in successfully!");
            return "redirect:/home/" + loginResponse.getId();
        }
        catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("message", "Login failed! Please try again.");
            redirectAttributes.addFlashAttribute("user", new LoginRequestDTO());
            return "redirect:/Authentication/login";
        }
    }
}