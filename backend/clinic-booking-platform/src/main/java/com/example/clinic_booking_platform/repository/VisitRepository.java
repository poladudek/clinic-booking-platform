package com.example.clinic_booking_platform.repository;

import com.example.clinic_booking_platform.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    // 1️Doctor + Patient query
    List<Visit> findByDoctorIdAndPatientPesel(Long doctorId, String pesel);

    // Only Doctor query
    List<Visit> findByDoctorId(Long doctorId);

    // Only Patient query
    List<Visit> findByPatientPesel(String pesel);


}

