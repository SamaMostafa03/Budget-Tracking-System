package com.BudgetTracking.UserService.exceptions;

public class RecordNotFoundExecption extends RuntimeException{
    public RecordNotFoundExecption() {
        super();
    }

    public RecordNotFoundExecption(String message) {
        super(message);
    }
}
