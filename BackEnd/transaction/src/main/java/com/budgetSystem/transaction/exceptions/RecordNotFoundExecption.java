package com.budgetSystem.transaction.exceptions;

public class RecordNotFoundExecption extends RuntimeException{
    public RecordNotFoundExecption() {
        super();
    }

    public RecordNotFoundExecption(String message) {
        super(message);
    }
}
