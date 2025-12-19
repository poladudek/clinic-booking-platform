package com.example.clinic_booking_platform.repository;

import com.example.clinic_booking_platform.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

