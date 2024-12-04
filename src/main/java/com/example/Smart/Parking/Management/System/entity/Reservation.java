package com.example.Smart.Parking.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "reservation" )
    private Bill bill;

    @OneToOne
    @JoinColumn(name = "parking_slot_id", nullable = false)
    @JsonIgnore
    private ParkingSlot parkingSlot;

    @NotBlank(message = "Vehicle number is required")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$", message = "Vehicle number must be in the format AA00BB0000")
    private String vehicleNumber;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time must be in the future")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    @Future(message = "End time must be in the future")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
