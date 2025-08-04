package com.clinic.repository;

import com.clinic.model.Appointment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class AppointmentRepository {

    private final Map<Long, Appointment> appointments = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Appointment save(Appointment appointment) {
        appointment.setId(idCounter.incrementAndGet());
        appointments.put(appointment.getId(), appointment);
        return appointment;
    }

    public Collection<Appointment> findAll() {
        return appointments.values();
    }

    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointments.values().stream()
                .filter(a -> a.getDoctorId().equals(doctorId))
                .collect(Collectors.toList());
    }

    public boolean isSlotBooked(Long doctorId, LocalDateTime slot) {
        return appointments.values().stream()
                .anyMatch(a -> a.getDoctorId().equals(doctorId) && a.getSlot().equals(slot));
    }
}
