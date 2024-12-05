package com.example.Smart.Parking.Management.System.service;

import com.example.Smart.Parking.Management.System.dto.BillDTO;
import com.example.Smart.Parking.Management.System.entity.Bill;

public interface BillService {
    BillDTO generateBill(Long reservationId);

    String payBill(Long billId);
}
