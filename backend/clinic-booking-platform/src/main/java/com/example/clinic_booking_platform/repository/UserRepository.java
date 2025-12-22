package com.example.clinic_booking_platform.repository;


import com.example.clinic_booking_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPesel(String pesel);

}
