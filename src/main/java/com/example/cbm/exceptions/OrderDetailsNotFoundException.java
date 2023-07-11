package com.example.cbm.exceptions;

public class OrderDetailsNotFoundException extends RuntimeException{
    public OrderDetailsNotFoundException(String msg)
    {
        super(msg);
    }
}
