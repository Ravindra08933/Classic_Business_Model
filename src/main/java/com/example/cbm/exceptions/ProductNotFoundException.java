package com.example.cbm.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String msg)
    {
        super(msg);
    }
}
