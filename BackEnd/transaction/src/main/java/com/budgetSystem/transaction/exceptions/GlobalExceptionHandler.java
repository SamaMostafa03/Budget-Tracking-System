package com.budgetSystem.transaction.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RecordNotFoundExecption.class)
    public ResponseEntity<?> handleRecordNotFound(RecordNotFoundExecption ex)
    {
        //logger.error("Record not found: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request)
    {
       // logger.error("Validation for arguments failed: {}", ex.getMessage());
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation for arguments failed", errors);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(error);
    }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleOtherExceptions(Throwable ex)
    {
       // logger.error("An unexpected error occurred: {}", ex.getMessage());
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.toString(), errors);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(error);
    }

}