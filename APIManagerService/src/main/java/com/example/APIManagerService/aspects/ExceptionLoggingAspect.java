package com.example.APIManagerService.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

    @Pointcut("execution(* com.example.APIManagerService.controller.*.*(..))")
    public void controllerMethods() {}

    @Pointcut("execution(* com.example.APIManagerService.service.*.*(..))")
    public void serviceMethods() {}

    @AfterThrowing(pointcut = "controllerMethods() || serviceMethods()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        logger.error("Exception in {}.{}: {}", className, methodName, ex.getMessage(), ex);

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            logger.error("Method arguments: ");
            for (int i = 0; i < args.length; i++) {
                logger.error("arg[{}]: {}", i, args[i]);
            }
        }
    }
}
