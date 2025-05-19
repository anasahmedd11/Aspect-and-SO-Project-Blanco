package com.example.APIManagerService.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserService {
    private final String baseUrl = "http://Blanco-Database-Service:8081/db-service/users";
    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Long getUserByEmail(String email) {
        String url = baseUrl + "/user/email/" + email;
        ResponseEntity<Long> useridResponse = restTemplate.getForEntity(url, Long.class);
        if (useridResponse.getStatusCode() == HttpStatus.NO_CONTENT)
            return null;
        else
            return useridResponse.getBody();
    }

    public String getUserEmailUserId(Long id) {
        String url = baseUrl + "/user/" + id;
        ResponseEntity<String> userResponse = restTemplate.getForEntity(url, String.class);
        return userResponse.getBody();
    }

}
