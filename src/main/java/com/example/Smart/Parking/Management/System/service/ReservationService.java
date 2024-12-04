package com.example.Smart.Parking.Management.System.service;

import com.example.Smart.Parking.Management.System.dto.ReservationDTO;
import com.example.Smart.Parking.Management.System.entity.Reservation;

public interface ReservationService {
    Reservation createReservation(ReservationDTO reservationDTO);

    Reservation cancelReservation(Long reservationId);

    Reservation getReservationDetails(Long reservationId);
}
