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
    public String loginPage(Model model, HttpServletResponse response) {
        Cookie tokenCookie = new Cookie("blanco-jwt", null);
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge(0);
        response.addCookie(tokenCookie);

        Cookie idCookie = new Cookie("active-user-id", null);
        idCookie.setPath("/");
        idCookie.setMaxAge(0);
        response.addCookie(idCookie);

        model.addAttribute("user", new LoginRequestDTO());
        return "login";
    }

    @RequestMapping(value = "/Authentication/loginForm", method = RequestMethod.POST)
    @PostMapping("/Authentication/loginForm")
    public String loginForm(Model model, LoginRequestDTO user,
                            RedirectAttributes redirectAttributes, HttpServletResponse response) {
        try {
            LoginResponseDTO loginResponse = authenticationService.login(user);

            Cookie tokenCookie = new Cookie("blanco-jwt", loginResponse.getToken());
            tokenCookie.setPath("/");
            tokenCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(tokenCookie);

            Cookie idCookie = new Cookie("active-user-id", loginResponse.getId().toString());
            idCookie.setPath("/");
            idCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(idCookie);

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