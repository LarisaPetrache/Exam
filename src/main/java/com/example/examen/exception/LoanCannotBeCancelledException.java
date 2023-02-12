package com.example.examen.exception;

public class LoanCannotBeCancelledException extends RuntimeException {
    public LoanCannotBeCancelledException(String message){
        super(message);
    }
}
