package com.example.clinic_booking_platform.controller;

import com.example.clinic_booking_platform.dto.VisitResponseDTO;
import com.example.clinic_booking_platform.entity.Visit;
import com.example.clinic_booking_platform.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    // DELETE VISIT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }

    // GET ALL VISITS
    @GetMapping
    public ResponseEntity<List<VisitResponseDTO>> getAllVisits() {
        return ResponseEntity.ok(visitService.getAllVisits());
    }

    // Doctor + Patient
    @GetMapping("/doctor/{doctorId}/patient/{pesel}")
    public ResponseEntity<List<VisitResponseDTO>> getVisitsByDoctorAndPatient(
            @PathVariable Long doctorId,
            @PathVariable String pesel
    ) {
        return ResponseEntity.ok(visitService.getVisitsByDoctorAndPatient(doctorId, pesel));
    }

    // Only Doctors
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<VisitResponseDTO>> getVisitsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(visitService.getVisitsByDoctor(doctorId));
    }

    //get Only Patients
    @GetMapping("/patient/{pesel}")
    public ResponseEntity<List<VisitResponseDTO>> getVisitsByPatient(@PathVariable String pesel) {
        return ResponseEntity.ok(visitService.getVisitsByPatient(pesel));
    }
}
