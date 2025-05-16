package com.example.AuthenticationService.Controllers;

import com.example.AuthenticationService.DTO.LoginRequest;
import com.example.AuthenticationService.DTO.RegisterRequest;
import com.example.AuthenticationService.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Authentication")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(authService.loginUser(loginRequest));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @GetMapping("/authenticated")
    @PreAuthorize("isAuthenticated()")
    public String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "User with email " + authentication.getName() + " is authenticated";
    }
}
