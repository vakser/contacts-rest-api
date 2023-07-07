package com.example.exception;

import org.springframework.http.HttpStatus;

public class ContactsApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ContactsApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
