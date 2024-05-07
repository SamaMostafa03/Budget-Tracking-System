package com.budgetSystem.transaction.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class TimeLogAspect {
    Logger log = LoggerFactory.getLogger(TimeLogAspect.class);

    @Around(value = "execution(* com.budgetSystem.transaction.TransactionController.*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Class Name: ").append(joinPoint.getSignature().getDeclaringTypeName())
                .append("\tMethod Name: ").append(joinPoint.getSignature().getName()).append("\tTook: ");
        Object returnValue = joinPoint.proceed();
        log.info(stringBuilder.append(System.currentTimeMillis() - startTime).append("ms").toString());
        return returnValue;
    }
}