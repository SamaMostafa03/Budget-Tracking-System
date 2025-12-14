package com.sama.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Aspect
@Order(1)
@Component
public class TimeLogAspect {
    Logger log = LoggerFactory.getLogger(TimeLogAspect.class);
    @Around("execution(* *(..)) && @within(restController)")
    public Object logTime(ProceedingJoinPoint joinPoint, RestController restController) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        }
        finally {
            log.info(
                    "class: {} Method: {}() Took: {}ms",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    System.currentTimeMillis() - startTime
            );

        }
    }
}