package com.example.Smart.Parking.Management.System.handler;

public class PaymentAlreadyCompletedException extends RuntimeException{
    public PaymentAlreadyCompletedException(String message)
    {
        super(message);
    }
}
