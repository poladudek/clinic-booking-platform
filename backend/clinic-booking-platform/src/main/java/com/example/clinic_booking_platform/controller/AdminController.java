package com.example.clinic_booking_platform.controller;

import com.example.clinic_booking_platform.dto.UserRequestDTO;
import com.example.clinic_booking_platform.dto.UserResponseDTO;
import com.example.clinic_booking_platform.entity.User;
import com.example.clinic_booking_platform.mapper.UserMapper;
import com.example.clinic_booking_platform.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/panel")
public class AdminController {

    private final AdminService adminService;
    private final UserMapper userMapper;

    public AdminController(AdminService adminService, UserMapper userMapper) {
        this.adminService = adminService;
        this.userMapper = userMapper;
    }

    // CREATE USER
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO requestDTO) {
        User created = adminService.createUser(userMapper.toEntity(requestDTO));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toResponse(created));
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // GET ALL USERS
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        List<UserResponseDTO> responseList = users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    // Get User by pesel
    @GetMapping("/pesel/{pesel}")
    public ResponseEntity<UserResponseDTO> getUserByPesel(@PathVariable String pesel) {
        User user = adminService.getUserByPesel(pesel);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

}
