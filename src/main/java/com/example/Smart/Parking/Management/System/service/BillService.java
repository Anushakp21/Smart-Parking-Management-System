package com.example.Smart.Parking.Management.System.service;

import com.example.Smart.Parking.Management.System.entity.Bill;

public interface BillService {
    Bill generateBill(Long reservationId);
    Bill updatePaymentStatus(Long billId, String paymentStatus);
}
