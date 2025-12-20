package com.example.clinic_booking_platform.repository;

import com.example.clinic_booking_platform.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}

