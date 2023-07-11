package com.example.cbm.exceptions;

public class OfficeNotFoundException extends RuntimeException{
    public OfficeNotFoundException(String msg)
    {
        super(msg);
    }
}
