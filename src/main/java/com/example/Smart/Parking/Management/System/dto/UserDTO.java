package com.example.Smart.Parking.Management.System.dto;

import com.example.Smart.Parking.Management.System.entity.Reservation;
import com.example.Smart.Parking.Management.System.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<String> registeredVehicles;


    public UserDTO(Long id, String name, String email, String phone, List<String> registeredVehicles) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.registeredVehicles=registeredVehicles;
    }
}
