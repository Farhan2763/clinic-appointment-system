package com.clinic.service;

import com.clinic.exception.CustomException;
import com.clinic.model.Doctor;
import com.clinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public Doctor addDoctor(Doctor doctor) {
        return repo.save(doctor);
    }

    public Doctor getDoctorById(Long id) {
        return repo.findById(id).orElseThrow(() -> new CustomException("Doctor not found"));
    }

    public Collection<Doctor> getAllDoctors() {
        return repo.findAll();
    }

    public List<LocalDateTime> getAvailableSlotsByDate(Long doctorId, LocalDate date) {
        return repo.findAvailableSlotsByDate(doctorId, date);
    }
}
