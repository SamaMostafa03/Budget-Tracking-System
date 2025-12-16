package com.sama.exceptions;

public class ExceedingAmountException extends RuntimeException{
    public ExceedingAmountException() {
        super();
    }

    public ExceedingAmountException(String message) {
        super(message);
    }
}
