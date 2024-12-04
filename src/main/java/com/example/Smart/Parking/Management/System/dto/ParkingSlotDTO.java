package com.example.Smart.Parking.Management.System.dto;

import com.example.Smart.Parking.Management.System.entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlotDTO {
    private Long id;
    private String slotNumber;
    private int level;
    private boolean isAvailable;
    private VehicleType vehicleType;
}
