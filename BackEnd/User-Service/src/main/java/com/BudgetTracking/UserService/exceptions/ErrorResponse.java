package com.BudgetTracking.UserService.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
public class ErrorResponse {
    private Boolean success;
    private String message;
    private LocalDateTime dateTime;
    private List<String> details;


    public ErrorResponse() {
        super();
    }

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
        this.success = Boolean.FALSE;
        this.dateTime = LocalDateTime.now();
    }
}
