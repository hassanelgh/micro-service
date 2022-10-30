package com.example.billingservice.exceptions;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(String s) {
        super(s);
    }
}
