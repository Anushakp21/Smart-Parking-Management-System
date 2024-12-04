package com.example.Smart.Parking.Management.System.dto;

import com.example.Smart.Parking.Management.System.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long id;
    private Long reservationId;
    private double amount;
    private PaymentStatus paymentStatus;
}
