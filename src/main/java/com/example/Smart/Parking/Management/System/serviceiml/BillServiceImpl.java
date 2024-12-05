package com.example.Smart.Parking.Management.System.serviceiml;

import com.example.Smart.Parking.Management.System.dto.BillDTO;
import com.example.Smart.Parking.Management.System.entity.Bill;
import com.example.Smart.Parking.Management.System.enums.PaymentStatus;
import com.example.Smart.Parking.Management.System.entity.Reservation;
import com.example.Smart.Parking.Management.System.handler.BillNotFoundExceptionOrExists;
import com.example.Smart.Parking.Management.System.handler.PaymentAlreadyCompletedException;
import com.example.Smart.Parking.Management.System.handler.ReservationConflictException;
import com.example.Smart.Parking.Management.System.repository.BillRepository;
import com.example.Smart.Parking.Management.System.repository.ReservationRepository;
import com.example.Smart.Parking.Management.System.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    EmailService emailService;

    private BillRepository billRepo;
    private ReservationRepository reservationRepo;
    public BillServiceImpl(BillRepository billRepo, ReservationRepository reservationRepo) {
        this.billRepo = billRepo;
        this.reservationRepo = reservationRepo;
    }


    @Override
    public BillDTO generateBill(Long reservationId) {
        Reservation reservation = reservationRepo.findById(reservationId).get();
        if(billRepo.existsByReservation(reservation)){
            throw new BillNotFoundExceptionOrExists("Bill already exists for this reservation");
        }
        Long duration = Duration.between(reservation.getStartTime(), reservation.getEndTime()).toMinutes();
        Double durationInMinutes = duration / 60.0;
        Bill bill = new Bill();
        if(reservation.getVehicleType().equalsIgnoreCase("Bike")) {
            bill.setAmount((durationInMinutes*1.0));
        }
        if(reservation.getVehicleType().equalsIgnoreCase("Car")) {
            bill.setAmount(durationInMinutes*3.0);
        }
        if(reservation.getVehicleType().equalsIgnoreCase("Truck")) {
            bill.setAmount(durationInMinutes*5.0);
        }
        bill.setPaymentStatus(PaymentStatus.valueOf("UNPAID"));
        bill.setReservation(reservation);
        return mapToDTO(billRepo.save(bill));
    }

    private BillDTO mapToDTO(Bill bill) {
        BillDTO billDTO = new BillDTO();
        billDTO.setReservationId(bill.getReservation().getReservationId());
        billDTO.setAmount(bill.getAmount());
        billDTO.setPaymentStatus(bill.getPaymentStatus());
        return billDTO;
    }

    @Override
    public String payBill(Long billId) {
        Bill bill = billRepo.findById(billId)
                .orElseThrow(() -> new BillNotFoundExceptionOrExists("Bill not found with id: " + billId));

        if (bill.getPaymentStatus() == PaymentStatus.PAID) {
            throw new PaymentAlreadyCompletedException("Bill payment already paid");
        }
        bill.setPaymentStatus(PaymentStatus.PAID);
        billRepo.save(bill);

        String subject = "Payment successful";
        String text = "You have paid your parking reservation bill " + bill.getAmount() + "$ successfully.\nVisit again...";
        emailService.sendEmail(bill.getReservation().getUser().getEmail(), subject, text);

        return "Payment Successful";
    }

}
