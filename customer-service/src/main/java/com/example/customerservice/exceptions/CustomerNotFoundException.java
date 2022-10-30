package com.example.customerservice.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String s) {
        super(s);
    }
}
