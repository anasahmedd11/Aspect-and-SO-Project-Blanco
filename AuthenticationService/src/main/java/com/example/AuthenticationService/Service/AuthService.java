package com.example.AuthenticationService.Service;

import com.example.AuthenticationService.DTO.JwtResponse;
import com.example.AuthenticationService.DTO.LoginRequest;
import com.example.AuthenticationService.DTO.RegisterRequest;
import com.example.AuthenticationService.Entity.User;
import com.example.AuthenticationService.Security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    private final RestTemplate restTemplate;
    private final String databaseServiceUrl = "http://Blanco-Database-Service:8081/db-service/users";

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<JwtResponse> loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();

        JwtResponse jwtResponse = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername());

        return ResponseEntity.ok(jwtResponse);
    }

    public ResponseEntity<?> registerUser(@Valid RegisterRequest registerRequest) {
        registerRequest.setPassword(encoder.encode(registerRequest.getPassword()));
        try {
            ResponseEntity<User> response = restTemplate.postForEntity(
                    databaseServiceUrl,
                    registerRequest,
                    User.class
            );
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists with this email");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getResponseBodyAsString());
        }
    }
}