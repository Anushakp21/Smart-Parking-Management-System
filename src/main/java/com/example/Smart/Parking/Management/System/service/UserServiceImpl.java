package com.example.Smart.Parking.Management.System.service;

import com.example.Smart.Parking.Management.System.dto.UserDTO;
import com.example.Smart.Parking.Management.System.entity.User;
import com.example.Smart.Parking.Management.System.handler.UserNotFoundException;
import com.example.Smart.Parking.Management.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void registerUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        userRepository.save(user);
    }


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setRegisteredVehicles(userDTO.getRegisteredVehicles());

        userRepository.save(existingUser);
    }
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRegisteredVehicles(userDTO.getRegisteredVehicles());
        return user;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRegisteredVehicles()
        );
    }
}
