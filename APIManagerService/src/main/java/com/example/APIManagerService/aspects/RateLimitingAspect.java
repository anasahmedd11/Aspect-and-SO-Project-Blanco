package com.example.APIManagerService.aspects;

import com.example.APIManagerService.exception.RateLimitExceededException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RateLimitingAspect {


    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RateLimitingAspect(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Around("@annotation(com.example.APIManagerService.aspects.RateLimiting)")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimiting rateLimiting = method.getAnnotation(RateLimiting.class);

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("Request context not found");
        }
        HttpServletRequest request = attrs.getRequest();
        String clientIp = request.getRemoteAddr();
        String key = String.format("rate_limit:%s:%s.%s", clientIp,
                method.getDeclaringClass().getSimpleName(), method.getName());

        Long count = redisTemplate.opsForValue().increment(key);
        if (count == 1) {
            redisTemplate.expire(key, rateLimiting.duration(), TimeUnit.SECONDS);
        }

        if (count > rateLimiting.limit()) {
            throw new RateLimitExceededException(
                    String.format("Rate limit exceeded. Maximum %d requests per %d seconds allowed.",
                            rateLimiting.limit(), rateLimiting.duration())
            );
        }

        return joinPoint.proceed();
    }

} 