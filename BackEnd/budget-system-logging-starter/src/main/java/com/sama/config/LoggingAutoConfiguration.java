package com.sama.config;

import com.sama.aspects.ExceptionLogAspect;
import com.sama.aspects.TimeLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAutoConfiguration {
    @Bean
    public TimeLogAspect timeLogAspect() {
        return new TimeLogAspect();
    }

    @Bean
    public ExceptionLogAspect exceptionLogAspect() {
        return new ExceptionLogAspect();
    }

}
