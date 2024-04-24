package com.example.Project06.exception;

import org.springframework.http.HttpStatus;

public class PaymentNotFoundException extends RuntimeException{

    private HttpStatus httpStatus;

    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException(String message, HttpStatus httpStatus) {

        super(message);
        this.httpStatus = httpStatus;
    }
}