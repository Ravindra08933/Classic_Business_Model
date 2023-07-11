package com.example.cbm.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String msg)
    {
        super(msg);
    }
}
