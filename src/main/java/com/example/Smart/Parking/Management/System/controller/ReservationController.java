package com.example.Smart.Parking.Management.System.controller;

import com.example.Smart.Parking.Management.System.dto.ReservationDTO;
import com.example.Smart.Parking.Management.System.entity.Reservation;
import com.example.Smart.Parking.Management.System.serviceiml.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationServiceImpl reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }


    @PutMapping("/{id}/cancel")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id) {
        Reservation canceledReservation = reservationService.cancelReservation(id);
        return new ResponseEntity<>(canceledReservation, HttpStatus.OK);
    }

    @GetMapping
    public List<ReservationDTO> getReservationDetails() {
       return reservationService.getReservationDetails();
    }
}
