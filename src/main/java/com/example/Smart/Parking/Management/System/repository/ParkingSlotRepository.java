package com.example.Smart.Parking.Management.System.repository;

import com.example.Smart.Parking.Management.System.entity.ParkingSlot;
import com.example.Smart.Parking.Management.System.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Long> {
    List<ParkingSlot> findByIsAvailableTrueAndVehicleType(VehicleType vehicleType);

    List<ParkingSlot> findByLevel(int level);

    ParkingSlot findBySlotNumber(String slotNumber);
}
