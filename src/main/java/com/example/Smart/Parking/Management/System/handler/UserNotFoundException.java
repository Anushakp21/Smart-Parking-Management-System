package com.example.Smart.Parking.Management.System.handler;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
