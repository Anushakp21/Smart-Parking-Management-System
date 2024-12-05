package com.example.Smart.Parking.Management.System.handler;

public class BillNotFoundExceptionOrExists extends RuntimeException{
    public BillNotFoundExceptionOrExists(String message)
    {
        super(message);
    }
}
