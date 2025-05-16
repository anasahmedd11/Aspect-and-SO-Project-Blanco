package com.example.APIManagerService.service;

import com.example.APIManagerService.DTO.Authentication.LoginRequestDTO;
import com.example.APIManagerService.DTO.Authentication.LoginResponseDTO;
import com.example.APIManagerService.DTO.Authentication.RegisterRequestDTO;
import com.example.APIManagerService.DTO.Authentication.RegisterResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {
    private final RestTemplate restTemplate;
    private final String AuthenticationServiceUrl = "http://Blanco-Authentication-Service:8082/Authentication";

    @Autowired
    public AuthenticationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<RegisterResponseDTO> register(RegisterRequestDTO registerRequestDTO) {
        String url = AuthenticationServiceUrl + "/register";
         return restTemplate.postForEntity(
                url,
                registerRequestDTO,
                RegisterResponseDTO.class
        );
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String url = AuthenticationServiceUrl + "/login";
        return restTemplate.postForObject(
                url,
                loginRequestDTO,
                LoginResponseDTO.class
        );
    }
}
