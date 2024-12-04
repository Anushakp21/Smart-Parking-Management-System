package com.example.Smart.Parking.Management.System.handler;

public class ReservationConflictException extends RuntimeException{
    public ReservationConflictException(String message)
    {
        super(message);
    }
}
