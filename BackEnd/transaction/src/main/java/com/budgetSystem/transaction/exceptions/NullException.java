package com.budgetSystem.transaction.exceptions;

public class NullException extends RuntimeException{
    public NullException() {
        super();
    }

    public NullException(String message) {
        super(message);
    }
}