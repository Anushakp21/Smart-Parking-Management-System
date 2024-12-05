package com.example.Smart.Parking.Management.System.dto;

import com.example.Smart.Parking.Management.System.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long reservationId;
    private double amount;
    private PaymentStatus paymentStatus;
    private BillDTO bill;
}
