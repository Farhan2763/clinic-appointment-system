package com.clinic.controller;

import com.clinic.model.Doctor;
import com.clinic.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(service.addDoctor(doctor));
    }

    @GetMapping
    public Collection<Doctor> getAll() {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id) {
        return service.getDoctorById(id);
    }

    @GetMapping("/{id}/slots")
    public ResponseEntity<List<java.time.LocalDateTime>> getAvailableSlots(@PathVariable Long id, @RequestParam String date) {
        return ResponseEntity.ok(service.getAvailableSlotsByDate(id, LocalDate.parse(date)));
    }
}
