package com.example.Smart.Parking.Management.System.dto;

import com.example.Smart.Parking.Management.System.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long userId;
    private Long slotId;
    private String vehicleNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    private String vehicleType;
    private BillDTO bill;
}
