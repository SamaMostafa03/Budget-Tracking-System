package com.budgetSystem.budget.exceptions;

public class RecordNotFoundExecption extends RuntimeException{
    public RecordNotFoundExecption() {
        super();
    }

    public RecordNotFoundExecption(String message) {
        super(message);
    }
}
