package com.example.Smart.Parking.Management.System.repository;

import com.example.Smart.Parking.Management.System.entity.ParkingSlot;
import com.example.Smart.Parking.Management.System.entity.Reservation;
import com.example.Smart.Parking.Management.System.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByStatus(ReservationStatus status);

    boolean existsByParkingSlotAndStartTimeLessThanAndEndTimeGreaterThan(ParkingSlot parkingSlot, LocalDateTime endTime, LocalDateTime startTime);
}
