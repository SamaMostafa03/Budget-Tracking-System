package com.sama.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class ExceptionLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionLogAspect.class);
    @AfterThrowing(
            pointcut =
                    "execution(* *(..)) && " +
                            "(@within(org.springframework.stereotype.Service))",
            throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Throwable ex) {

        logger.error(
                    "Exception in class: {} Method: {}() message: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    ex.getMessage()
            );
    }

}