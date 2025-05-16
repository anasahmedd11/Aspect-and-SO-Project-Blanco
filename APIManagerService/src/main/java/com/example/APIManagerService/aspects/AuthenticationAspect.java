package com.example.APIManagerService.aspects;

import com.example.APIManagerService.exception.UnauthorizedException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthenticationAspect {

    @Autowired
    private final RestTemplate restTemplate = new RestTemplate();
    private final String AuthenticationServiceUrl = "http://Blanco-Authentication-Service:8082/Authentication/is-authenticated";

    @Pointcut("execution(* com.example.APIManagerService.controller..*(..))")
    public void allControllerMethods() {}

    @Pointcut("execution(* com.example.APIManagerService.controller.LoginController..*(..))")
    public void loginControllerMethods() {}

    @Pointcut("execution(* com.example.APIManagerService.controller.RegisterController..*(..))")
    public void registerControllerMethods() {}

    //@Before("allControllerMethods() && ! loginControllerMethods() && ! registerControllerMethods()")
    public void checkAuthentication() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        if (request.getCookies() == null) {
            throw new UnauthorizedException("User must be logged in to access this resource.");
        }

        String token = null;
        for (Cookie cookie : request.getCookies()) {
            if ("blanco-jwt".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("User must be logged in to access this resource.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        try {
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    AuthenticationServiceUrl,
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );
            if (response.getStatusCode() != HttpStatus.OK)
                throw new UnauthorizedException("User must be logged in to access this resource.");

        } catch (HttpClientErrorException e) {
            throw new UnauthorizedException("User must be logged in to access this resource.");
        }
    }
}