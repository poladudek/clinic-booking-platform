package com.example.clinic_booking_platform.service;

import com.example.clinic_booking_platform.entity.User;
import com.example.clinic_booking_platform.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Walidacja pól wymaganych
        if (!StringUtils.hasText(user.getFirstName())) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (!StringUtils.hasText(user.getLastName())) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (!StringUtils.hasText(user.getPesel()) || user.getPesel().length() != 11) {
            throw new IllegalArgumentException("PESEL must be 11 characters");
        }
        if (user.getRole() == null) {
            throw new IllegalArgumentException("Role is required");
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        // Walidacja specjalizacji dla lekarza
        if (user.getRole().name().equals("DOCTOR") && !StringUtils.hasText(user.getSpecialization())) {
            throw new IllegalArgumentException("Specialization is required for doctors");
        }

        // Zapis do bazy
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(userId);
    }

    public User getUserByPesel(String pesel) {
        return userRepository.findByPesel(pesel)
                .orElseThrow(() -> new IllegalArgumentException("User with PESEL " + pesel + " not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
