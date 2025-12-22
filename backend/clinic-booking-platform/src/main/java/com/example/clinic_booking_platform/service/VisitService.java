package com.example.clinic_booking_platform.service;

import com.example.clinic_booking_platform.entity.User;
import com.example.clinic_booking_platform.entity.Visit;
import com.example.clinic_booking_platform.repository.UserRepository;
import com.example.clinic_booking_platform.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final UserRepository userRepository;

    public VisitService(VisitRepository visitRepository, UserRepository userRepository) {
        this.visitRepository = visitRepository;
        this.userRepository = userRepository;
    }

    public Visit reserveVisit(Long visitId, Long patientId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        if (visit.isReserved()) {
            throw new RuntimeException("Visit already reserved");
        }

        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        visit.setPatient(patient);
        visit.setReserved(true);

        return visitRepository.save(visit);
    }

    public void deleteVisit(Long visitId) {
        visitRepository.deleteById(visitId);
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

// ----------------- zdobywanie ludzi z bazy danych  do grafikow ------------

    // 1️⃣ Doctor + Patient
    public List<Visit> getVisitsByDoctorAndPatient(Long doctorId, String pesel) {
        return visitRepository.findByDoctorIdAndPatientPesel(doctorId, pesel);
    }

    // 2️⃣ Only Doctor
    public List<Visit> getVisitsByDoctor(Long doctorId) {
        return visitRepository.findByDoctorId(doctorId);
    }

    // 3️⃣ Only Patient
    public List<Visit> getVisitsByPatient(String pesel) {
        return visitRepository.findByPatientPesel(pesel);
    }


}
