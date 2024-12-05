package com.example.Smart.Parking.Management.System.controller;

import com.example.Smart.Parking.Management.System.dto.ParkingSlotDTO;
import com.example.Smart.Parking.Management.System.entity.ParkingSlot;
import com.example.Smart.Parking.Management.System.enums.VehicleType;
import com.example.Smart.Parking.Management.System.serviceiml.ParkingSlotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/parking-slots")
public class ParkingSlotController {
    @Autowired
    ParkingSlotServiceImpl parkingSlotService;


    @PostMapping("/addslot")
    public ResponseEntity<ParkingSlotDTO> addParkingSlot(@RequestBody ParkingSlotDTO parkingSlotDTO) {
        return new ResponseEntity<>(parkingSlotService.addParkingSlot(parkingSlotDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<HashMap<String,String>> getAllSlots() {
        HashMap<String,String> parkingSlotDTOs = parkingSlotService.getAllSlotStatuses();
        return ResponseEntity.ok(parkingSlotDTOs);
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveSlot(@RequestParam Long slotId, @RequestParam String vehicleType) {
        parkingSlotService.reserveSlot(slotId, vehicleType);
        return ResponseEntity.ok("Slot reserved successfully!");
    }

    @PostMapping("/release/{id}")
    public ResponseEntity<String> releaseSlot(@PathVariable Long id) {
        parkingSlotService.releaseSlot(id);
        return ResponseEntity.ok("Slot released successfully!");
    }
    @GetMapping("/filter")
    public List<ParkingSlot> filterSlots(
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) VehicleType vehicleType) {

        if (level != null && vehicleType != null) {
            return parkingSlotService.getSlotsByLevelAndVehicleType(level, vehicleType);
        } else if (level != null) {
            return parkingSlotService.getSlotsByLevel(level);
        } else if (vehicleType != null) {
            return parkingSlotService.getSlotsByVehicleType(vehicleType);
        }
        else
        {
            return null;
        }
    }
}
