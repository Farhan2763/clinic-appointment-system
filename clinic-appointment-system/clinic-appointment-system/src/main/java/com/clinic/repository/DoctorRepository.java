package com.clinic.repository;

import com.clinic.model.Doctor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class DoctorRepository {

    private final Map<Long, Doctor> doctors = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Doctor save(Doctor doctor) {
        doctor.setId(idCounter.incrementAndGet());
        doctors.put(doctor.getId(), doctor);
        return doctor;
    }

    public Optional<Doctor> findById(Long id) {
        return Optional.ofNullable(doctors.get(id));
    }

    public Collection<Doctor> findAll() {
        return doctors.values();
    }

    public List<LocalDateTime> findAvailableSlotsByDate(Long doctorId, LocalDate date) {
        return doctors.get(doctorId).getAvailableSlots().stream()
                .filter(slot -> slot.toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public void updateDoctor(Doctor doctor) {
        doctors.put(doctor.getId(), doctor);
    }
}
