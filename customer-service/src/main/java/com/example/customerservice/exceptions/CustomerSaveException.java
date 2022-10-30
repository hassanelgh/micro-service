package com.example.customerservice.exceptions;

public class CustomerSaveException extends RuntimeException {
    public CustomerSaveException(String e) {
        super(e);
    }
}
