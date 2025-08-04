package com.clinic.service;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PatientService {
    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public Patient registerPatient(Patient patient) {
        return repo.save(patient);
    }

    public Collection<Patient> getAllPatients() {
        return repo.findAll();
    }
}