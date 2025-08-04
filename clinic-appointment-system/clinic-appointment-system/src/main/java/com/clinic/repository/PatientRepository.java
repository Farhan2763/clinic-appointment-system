package com.clinic.repository;

import com.clinic.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PatientRepository {

    private final Map<Long, Patient> patients = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Patient save(Patient patient) {
        patient.setId(idCounter.incrementAndGet());
        patients.put(patient.getId(), patient);
        return patient;
    }

    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(patients.get(id));
    }

    public Collection<Patient> findAll() {
        return patients.values();
    }
}
