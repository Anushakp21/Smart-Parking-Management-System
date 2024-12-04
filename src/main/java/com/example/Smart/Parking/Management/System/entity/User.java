package com.example.Smart.Parking.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @ElementCollection
    @CollectionTable(name = "user_registered_vehicles", joinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private List<String> registeredVehicles;

}