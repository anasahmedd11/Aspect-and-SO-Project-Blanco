package com.example.APIManagerService.exception;

import com.example.APIManagerService.DTO.Authentication.LoginRequestDTO;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({MissingRequestCookieException.class, UnauthorizedException.class})
    public String redirectToLogin(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Please login first!");
        redirectAttributes.addFlashAttribute("user", new LoginRequestDTO());
        return "redirect:/Authentication/login";
    }

    @ExceptionHandler({RateLimitExceededException.class})
    public String redirectToHome(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Too many requests! Please wait 1 minute.");
        return "redirect:/home";
    }

//    @ExceptionHandler({Exception.class})
//    public String genericError(RedirectAttributes redirectAttributes) {
//        redirectAttributes.addFlashAttribute("message", "Something went wrong. Please try again.");
//        return "redirect:/home";
//    }
}