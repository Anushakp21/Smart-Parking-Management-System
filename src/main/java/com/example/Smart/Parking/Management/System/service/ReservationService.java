package com.example.Smart.Parking.Management.System.service;

import com.example.Smart.Parking.Management.System.dto.ReservationDTO;
import com.example.Smart.Parking.Management.System.entity.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(ReservationDTO reservationDTO);

    Reservation cancelReservation(Long reservationId);

    List<ReservationDTO> getReservationDetails();
}
