package com.sama.config;

import com.sama.exceptions.GlobalExceptionHandler;
import com.sama.exceptions.NullException;
import com.sama.exceptions.RecordNotFoundException;
import com.sama.exceptions.ErrorResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionAutoConfiguration {
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public RecordNotFoundException recordNotFoundException() {
        return new RecordNotFoundException();
    }

    @Bean
    public NullException nullException() {
        return new NullException();
    }

    @Bean
    public ErrorResponse errorResponse() {
        return new ErrorResponse();
    }

}
