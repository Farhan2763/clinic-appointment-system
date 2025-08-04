package com.clinic.controller;

import com.clinic.model.Patient;
import com.clinic.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Patient> register(@RequestBody Patient patient) {
        return ResponseEntity.ok(service.registerPatient(patient));
    }

    @GetMapping
    public Collection<Patient> getAll() {
        return service.getAllPatients();
    }
}
