package com.example.Smart.Parking.Management.System.entity;

import com.example.Smart.Parking.Management.System.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slotNumber;

    private int level;

    @Column(nullable = false)
    private Boolean isAvailable=true;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}

