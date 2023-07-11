package com.example.cbm.exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String msg)
    {
        super(msg);
    }
}
