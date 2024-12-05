package com.example.Smart.Parking.Management.System.serviceiml;

import com.example.Smart.Parking.Management.System.dto.ParkingSlotDTO;
import com.example.Smart.Parking.Management.System.entity.ParkingSlot;
import com.example.Smart.Parking.Management.System.enums.VehicleType;
import com.example.Smart.Parking.Management.System.handler.SlotUnavailableException;
import com.example.Smart.Parking.Management.System.repository.ParkingSlotRepository;
import com.example.Smart.Parking.Management.System.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

    @Autowired
    ParkingSlotRepository parkingSlotRepository;


    @Override
    public HashMap<String, String> getAllSlotStatuses() {
        HashMap<String, String> status = new HashMap<>();
        List<ParkingSlot> parkingSlots = parkingSlotRepository.findAll();
        parkingSlots.forEach(parkingSlot -> {status.put(parkingSlot.getSlotNumber(), parkingSlot.getIsAvailable()?"available":"occupied");});
        return status;
    }

    @Override
    public ParkingSlot reserveSlot(Long slotId, String vehicleType) {
        ParkingSlot slot = parkingSlotRepository.findById(slotId)
                .orElseThrow(() -> new SlotUnavailableException("Parking slot not found"));

        if (!slot.getIsAvailable()) {
            throw new SlotUnavailableException("Parking slot is already reserved");
        }

        if (!slot.getVehicleType().name().equalsIgnoreCase(vehicleType)) {
            throw new SlotUnavailableException("Parking slot not suitable for the vehicle type");
        }

        slot.setIsAvailable(false); // Mark as reserved
        return parkingSlotRepository.save(slot);
    }

    @Override
    public ParkingSlot releaseSlot(Long slotId) {
        ParkingSlot slot = parkingSlotRepository.findById(slotId)
                .orElseThrow(() -> new SlotUnavailableException("Parking slot not found"));

        if (slot.getIsAvailable()) {
            throw new SlotUnavailableException("Parking slot is already available");
        }

        slot.setIsAvailable(true); // Mark as available
        return parkingSlotRepository.save(slot);
    }

    @Override
    public ParkingSlotDTO addParkingSlot(ParkingSlotDTO parkingSlotDTO) {
        ParkingSlot getParkingSlot = parkingSlotRepository.findBySlotNumber(parkingSlotDTO.getSlotNumber());
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setSlotNumber(parkingSlotDTO.getSlotNumber());
        parkingSlot.setLevel(parkingSlotDTO.getLevel());
        parkingSlot.setVehicleType(parkingSlotDTO.getVehicleType());
        parkingSlot.setIsAvailable(parkingSlotDTO.getIsAvailable());
        if(getParkingSlot==null) {
            parkingSlotRepository.save(parkingSlot);
            return parkingSlotDTO;
        }
        else
            throw new SlotUnavailableException("Slot number "+parkingSlot.getSlotNumber()+" already exists");
    }
    // Filter by level and vehicle type
    public List<ParkingSlot> getSlotsByLevelAndVehicleType(int level, VehicleType vehicleType) {
        return parkingSlotRepository.findByLevelAndVehicleType(level, vehicleType);
    }

    // Filter by level only
    public List<ParkingSlot> getSlotsByLevel(int level) {
        return parkingSlotRepository.findByLevel(level);
    }

    // Filter by vehicle type only
    public List<ParkingSlot> getSlotsByVehicleType(VehicleType vehicleType) {
        return parkingSlotRepository.findByVehicleType(vehicleType);
    }
}
