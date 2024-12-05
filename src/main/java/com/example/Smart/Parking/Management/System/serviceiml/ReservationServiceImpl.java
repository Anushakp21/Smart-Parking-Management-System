package com.example.Smart.Parking.Management.System.serviceiml;

import com.example.Smart.Parking.Management.System.dto.BillDTO;
import com.example.Smart.Parking.Management.System.dto.ReservationDTO;
import com.example.Smart.Parking.Management.System.entity.ParkingSlot;
import com.example.Smart.Parking.Management.System.entity.Reservation;
import com.example.Smart.Parking.Management.System.enums.ReservationStatus;
import com.example.Smart.Parking.Management.System.entity.User;
import com.example.Smart.Parking.Management.System.handler.ReservationConflictException;
import com.example.Smart.Parking.Management.System.handler.SlotUnavailableException;
import com.example.Smart.Parking.Management.System.handler.UserNotFoundException;
import com.example.Smart.Parking.Management.System.repository.ParkingSlotRepository;
import com.example.Smart.Parking.Management.System.repository.ReservationRepository;
import com.example.Smart.Parking.Management.System.repository.UserRepository;
import com.example.Smart.Parking.Management.System.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO) {
        User user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Fetch the ParkingSlot entity
        ParkingSlot parkingSlot = parkingSlotRepository.findById(reservationDTO.getSlotId())
                .orElseThrow(() -> new SlotUnavailableException("Slot not found or unavailable"));

        if (!parkingSlot.getIsAvailable()) {
            throw new SlotUnavailableException("Slot is unavailable");
        }
        if (!parkingSlot.getVehicleType().name().equalsIgnoreCase(reservationDTO.getVehicleType())) {
            throw new SlotUnavailableException("Slot not suitable for the vehicle type");
        }

        // Check for reservation conflicts
        boolean conflict = reservationRepository.existsByParkingSlotAndStartTimeLessThanAndEndTimeGreaterThan(
                parkingSlot, reservationDTO.getEndTime(), reservationDTO.getStartTime());
        if (conflict) {
            throw new ReservationConflictException("Slot timing conflicts with an existing reservation");
        }

        // Create the Reservation entity
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setParkingSlot(parkingSlot);
        reservation.setVehicleNumber(reservationDTO.getVehicleNumber());
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.setVehicleType(reservationDTO.getVehicleType());

        // Save the reservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Mark the parking slot as unavailable
        parkingSlot.setIsAvailable(false);
        parkingSlotRepository.save(parkingSlot);

        return savedReservation;
    }

    @Override
    public Reservation cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (reservation.getStatus() == ReservationStatus.ACTIVE) {
            reservation.setStatus(ReservationStatus.CANCELLED);

            ParkingSlot slot = reservation.getParkingSlot(); // Access the ParkingSlot object
            slot.setIsAvailable(true);
            parkingSlotRepository.save(slot);
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationDTO> getReservationDetails() {
        List<Reservation> reservations=reservationRepository.findAll();
        List<ReservationDTO> responseDtos=new ArrayList<>();
        for(Reservation employee:reservations)
        {
            ReservationDTO responseDto=mapToResponse(employee);
            responseDtos.add(responseDto);
        }
        return  responseDtos;
    }

    private ReservationDTO mapToResponse(Reservation reservation) {
       ReservationDTO responseDto=new ReservationDTO();
       responseDto.setUserId(reservation.getUser().getId());
       responseDto.setSlotId(reservation.getParkingSlot().getId());
       responseDto.setVehicleNumber(reservation.getVehicleNumber());
       responseDto.setStartTime(reservation.getStartTime());
       responseDto.setEndTime(reservation.getEndTime());
       responseDto.setStatus(reservation.getStatus());
       responseDto.setVehicleType(reservation.getVehicleType());

       return responseDto;
    }

}
