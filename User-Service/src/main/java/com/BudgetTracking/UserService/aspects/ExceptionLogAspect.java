package com.BudgetTracking.UserService.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class ExceptionLogAspect {

    Logger logger = LoggerFactory.getLogger(ExceptionLogAspect.class);

    @Around("execution(* com.BudgetTracking.UserService.exceptions.GlobalExceptionHandler.*(..))")
    public Object logException(ProceedingJoinPoint joinPoint)throws Throwable {
        StringBuilder errorMessage = new StringBuilder("Exception:");
        Exception exception = (Exception) joinPoint.getArgs()[0];
        errorMessage.append(exception.toString());
        Object returnValue = joinPoint.proceed();
        logger.error(errorMessage.toString());
        return returnValue;
    }
}