package com.example.clinic_booking_platform.controller;

import com.example.clinic_booking_platform.service.AdminService;
import org.springframework.web.bind.annotation.*;
import com.example.clinic_booking_platform.entity.User;

import java.util.List;

@RestController
@RequestMapping("/api/panel")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService userService) {
        this.adminService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return adminService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

}
