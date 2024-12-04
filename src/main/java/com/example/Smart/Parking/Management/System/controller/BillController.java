package com.example.Smart.Parking.Management.System.controller;

import com.example.Smart.Parking.Management.System.entity.Bill;
import com.example.Smart.Parking.Management.System.service.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    @Autowired
    private BillServiceImpl billService;


    @PostMapping("/generate/{reservationId}")
    public ResponseEntity<Bill> generateBill(@PathVariable Long reservationId) {
        Bill bill = billService.generateBill(reservationId);
        return ResponseEntity.ok(bill);
    }


    @PatchMapping("/update/{billId}")
    public ResponseEntity<Bill> updatePaymentStatus(@PathVariable Long billId,
                                                    @RequestParam String paymentStatus) {
        Bill updatedBill = billService.updatePaymentStatus(billId, paymentStatus);
        return ResponseEntity.ok(updatedBill);
    }
}
