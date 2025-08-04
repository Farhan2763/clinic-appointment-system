package com.clinic.controller;

import com.clinic.model.Appointment;
import com.clinic.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Appointment> book(@RequestParam Long doctorId, @RequestParam Long patientId, @RequestParam String slot) {
        return ResponseEntity.ok(service.bookAppointment(doctorId, patientId, LocalDateTime.parse(slot)));
    }

    @GetMapping
    public Collection<Appointment> getAll() {
        return service.getAllAppointments();
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getByDoctor(@PathVariable Long doctorId) {
        return service.getAppointmentsByDoctor(doctorId);
    }

    @GetMapping("/doctor/{doctorId}/date")
    public List<Appointment> getByDoctorAndDate(@PathVariable Long doctorId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return service.getAppointmentsByDoctor(doctorId).stream()
                .filter(a -> a.getSlot().toLocalDate().equals(localDate))
                .collect(Collectors.toList());
    }
}
