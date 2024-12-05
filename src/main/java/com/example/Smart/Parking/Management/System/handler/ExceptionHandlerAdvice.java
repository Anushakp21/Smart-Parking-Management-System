package com.example.Smart.Parking.Management.System.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(SlotUnavailableException.class)
    public ResponseEntity<String> handleSlotUnavailable(SlotUnavailableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ReservationConflictException.class)
    public ResponseEntity<String> reservation(ReservationConflictException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BillNotFoundExceptionOrExists.class)
    public ResponseEntity<String> billNotFound(ReservationConflictException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PaymentAlreadyCompletedException.class)
    public ResponseEntity<String> handlePaymentAlreadyCompletedException(PaymentAlreadyCompletedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
