package com.example.Smart.Parking.Management.System.service;

import com.example.Smart.Parking.Management.System.dto.ParkingSlotDTO;
import com.example.Smart.Parking.Management.System.entity.ParkingSlot;

import java.util.HashMap;
import java.util.List;

public interface ParkingSlotService {
    HashMap<String ,String> getAllSlotStatuses();

    ParkingSlot reserveSlot(Long slotId, String vehicleType);

    ParkingSlot releaseSlot(Long slotId);

    ParkingSlotDTO addParkingSlot(ParkingSlotDTO parkingSlotDTO);
}
