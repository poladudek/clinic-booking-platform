package com.example.clinic_booking_platform.mapper;

import com.example.clinic_booking_platform.dto.UserRequestDTO;
import com.example.clinic_booking_platform.dto.UserResponseDTO;
import com.example.clinic_booking_platform.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // RequestDTO -> Entity
    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPesel(dto.getPesel());
        user.setRole(dto.getRole());
        user.setSpecialization(dto.getSpecialization());
        user.setPassword(dto.getPassword());
        return user;
    }

    // Entity -> ResponseDTO
    public UserResponseDTO toResponse(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPesel(user.getPesel());
        dto.setRole(user.getRole());
        dto.setSpecialization(user.getSpecialization());
        return dto;
    }
}
