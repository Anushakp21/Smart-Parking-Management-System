package com.example.Smart.Parking.Management.System.service;

import com.example.Smart.Parking.Management.System.dto.UserDTO;

import java.util.List;

public interface UserService {
    void registerUser(UserDTO userDTO);


    List<UserDTO> getAllUsers();

    void updateUser(Long id, UserDTO userDTO);
}
