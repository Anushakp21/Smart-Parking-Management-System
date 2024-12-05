package com.example.Smart.Parking.Management.System.dto;

import com.example.Smart.Parking.Management.System.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlotDTO {
    private String slotNumber;
    private int level;
    private Boolean isAvailable;
    private VehicleType vehicleType;
}
