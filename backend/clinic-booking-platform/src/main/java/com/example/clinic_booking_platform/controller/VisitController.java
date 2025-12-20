package com.example.clinic_booking_platform.controller;

import com.example.clinic_booking_platform.entity.Visit;
import com.example.clinic_booking_platform.service.VisitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    // Reserve visit
    @PostMapping("/{visitId}/reserve/{patientId}")
    public Visit reserveVisit(
            @PathVariable Long visitId,
            @PathVariable Long patientId
    ) {
        return visitService.reserveVisit(visitId, patientId);
    }

    // Delete visit
    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
    }

    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

}
