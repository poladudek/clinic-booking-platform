package com.example.clinic_booking_platform.service;

import com.example.clinic_booking_platform.dto.VisitResponseDTO;
import com.example.clinic_booking_platform.entity.User;
import com.example.clinic_booking_platform.entity.Visit;
import com.example.clinic_booking_platform.mapper.VisitMapper;
import com.example.clinic_booking_platform.repository.UserRepository;
import com.example.clinic_booking_platform.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final VisitMapper visitMapper;

    public VisitService(VisitRepository visitRepository, UserRepository userRepository, VisitMapper visitMapper) {
        this.visitRepository = visitRepository;
        this.userRepository = userRepository;
        this.visitMapper = visitMapper;
    }

    public VisitResponseDTO reserveVisit(Long visitId, Long patientId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found"));

        if (visit.isReserved()) {
            throw new IllegalArgumentException("Visit already reserved");
        }

        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        visit.setPatient(patient);
        visit.setReserved(true);

        return visitMapper.toResponse(visitRepository.save(visit));
    }

    public void deleteVisit(Long visitId) {
        if (!visitRepository.existsById(visitId)) {
            throw new IllegalArgumentException("Visit not found");
        }
        visitRepository.deleteById(visitId);
    }

    public List<VisitResponseDTO> getAllVisits() {
        return visitRepository.findAll()
                .stream()
                .map(visitMapper::toResponse)
                .toList();
    }

    public List<VisitResponseDTO> getVisitsByDoctorAndPatient(Long doctorId, String pesel) {
        return visitRepository.findByDoctorIdAndPatientPesel(doctorId, pesel)
                .stream()
                .map(visitMapper::toResponse)
                .toList();
    }

    public List<VisitResponseDTO> getVisitsByDoctor(Long doctorId) {
        return visitRepository.findByDoctorId(doctorId)
                .stream()
                .map(visitMapper::toResponse)
                .toList();
    }

    public List<VisitResponseDTO> getVisitsByPatient(String pesel) {
        return visitRepository.findByPatientPesel(pesel)
                .stream()
                .map(visitMapper::toResponse)
                .toList();
    }
}
