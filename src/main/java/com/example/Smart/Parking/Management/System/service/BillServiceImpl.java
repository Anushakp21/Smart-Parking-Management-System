package com.example.Smart.Parking.Management.System.service;

import com.example.Smart.Parking.Management.System.entity.Bill;
import com.example.Smart.Parking.Management.System.entity.PaymentStatus;
import com.example.Smart.Parking.Management.System.entity.Reservation;
import com.example.Smart.Parking.Management.System.repository.BillRepository;
import com.example.Smart.Parking.Management.System.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Bill generateBill(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Determine the rate based on vehicle type
        double ratePerHour = getRateByVehicleType(reservation.getVehicleNumber());

        // Calculate the duration in hours
        long durationInHours = reservation.getEndTime().getHour() - reservation.getStartTime().getHour();

        // Calculate the bill amount
        double amount = ratePerHour * durationInHours;

        // Create the Bill object
        Bill bill = new Bill();
        bill.setReservation(reservation);
        bill.setAmount(amount);
        bill.setPaymentStatus(PaymentStatus.valueOf("UNPAID"));

        // Save and return the Bill
        return billRepository.save(bill);
    }

    @Override
    public Bill updatePaymentStatus(Long billId, String paymentStatus) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        bill.setPaymentStatus(PaymentStatus.valueOf(paymentStatus));
        return billRepository.save(bill);
    }
    private double getRateByVehicleType(String vehicleNumber) {
        // Define the vehicle rates
        if (vehicleNumber.startsWith("BIKE")) {
            return 1.0; // $1/hr for bike
        } else if (vehicleNumber.startsWith("CAR")) {
            return 3.0; // $3/hr for car
        } else if (vehicleNumber.startsWith("TRUCK")) {
            return 5.0; // $5/hr for truck
        }
        return 0.0; // Default rate
    }
}
