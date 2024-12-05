package com.example.Smart.Parking.Management.System.controller;

import com.example.Smart.Parking.Management.System.dto.BillDTO;
import com.example.Smart.Parking.Management.System.entity.Bill;
import com.example.Smart.Parking.Management.System.serviceiml.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    @Autowired
    private BillServiceImpl billService;


    @PostMapping("generate/{reservationId}")
    public ResponseEntity<BillDTO> generateBill(@PathVariable Long reservationId) {
        return new ResponseEntity<>(billService.generateBill(reservationId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/pay/{billId}")
    public ResponseEntity<String> payBill(@PathVariable Long billId) {
        return new ResponseEntity<>(billService.payBill(billId), HttpStatus.ACCEPTED);
    }
}
