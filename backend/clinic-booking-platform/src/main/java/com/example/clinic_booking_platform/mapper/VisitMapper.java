package com.example.clinic_booking_platform.mapper;

import com.example.clinic_booking_platform.dto.VisitRequestDTO;
import com.example.clinic_booking_platform.dto.VisitResponseDTO;
import com.example.clinic_booking_platform.entity.User;
import com.example.clinic_booking_platform.entity.Visit;
import org.springframework.stereotype.Component;

@Component
public class VisitMapper {

    public Visit toEntity(VisitRequestDTO dto, User doctor, User patient) {
        Visit visit = new Visit();
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        visit.setStartTime(dto.getStartTime());
        visit.setEndTime(dto.getEndTime());
        visit.setDescription(dto.getDescription());
        visit.setRecommendations(dto.getRecommendations());
        visit.setReserved(patient != null); // jeśli jest przypisany pacjent -> reserved
        return visit;
    }

    public VisitResponseDTO toResponse(Visit visit) {
        VisitResponseDTO dto = new VisitResponseDTO();
        dto.setId(visit.getId());
        if (visit.getDoctor() != null) {
            dto.setDoctorId(visit.getDoctor().getId());
            dto.setDoctorName(visit.getDoctor().getFirstName() + " " + visit.getDoctor().getLastName());
        }
        if (visit.getPatient() != null) {
            dto.setPatientId(visit.getPatient().getId());
            dto.setPatientName(visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName());
        }
        dto.setReserved(visit.isReserved());
        dto.setDescription(visit.getDescription());
        dto.setRecommendations(visit.getRecommendations());
        dto.setStartTime(visit.getStartTime().toString());
        dto.setEndTime(visit.getEndTime().toString());
        return dto;
    }
}
