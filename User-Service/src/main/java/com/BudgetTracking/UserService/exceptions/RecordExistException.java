package com.BudgetTracking.UserService.exceptions;

public class RecordExistException extends RuntimeException{
    public RecordExistException() {
        super();
    }

    public RecordExistException(String message) {
        super(message);
    }
}
