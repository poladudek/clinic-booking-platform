package com.example.clinic_booking_platform.controller;

import com.example.clinic_booking_platform.dto.UserRequestDTO;
import com.example.clinic_booking_platform.dto.UserResponseDTO;
import com.example.clinic_booking_platform.dto.VisitRequestDTO;
import com.example.clinic_booking_platform.dto.VisitResponseDTO;
import com.example.clinic_booking_platform.entity.User;
import com.example.clinic_booking_platform.entity.Visit;
import com.example.clinic_booking_platform.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    // ADD VISIT
    @PostMapping("/doctor")
    public ResponseEntity<VisitResponseDTO> addVisit(@RequestBody VisitRequestDTO requestDTO) {
        VisitResponseDTO createdVisit = visitService.addVisit(
                requestDTO.getDoctorId(),
                requestDTO.getStartTime(),
                requestDTO.getEndTime()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVisit);
    }

    // RESERVE VISIT
    @PostMapping("/patient")
    public ResponseEntity<VisitResponseDTO> reserveVisit(@RequestBody VisitRequestDTO requestDTO) {
        VisitResponseDTO reservedVisit = visitService.reserveVisit(
                requestDTO.getVisitId(),
                requestDTO.getPatientId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(reservedVisit);
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

    // GET MOST RECENT VISIT
    @GetMapping("/patient/next")
    public ResponseEntity<VisitResponseDTO> getNextVisit() {
        VisitResponseDTO nextVisit = visitService.getNextVisit();
        return ResponseEntity.ok(nextVisit);
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
