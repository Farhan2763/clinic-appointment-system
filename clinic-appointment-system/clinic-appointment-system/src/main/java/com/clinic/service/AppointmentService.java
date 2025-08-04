package com.clinic.service;

import com.clinic.exception.CustomException;
import com.clinic.model.Appointment;
import com.clinic.repository.AppointmentRepository;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public AppointmentService(AppointmentRepository appointmentRepo, DoctorRepository doctorRepo, PatientRepository patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    public synchronized Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime slot) {
        if (!doctorRepo.findById(doctorId).isPresent())
            throw new CustomException("Doctor not found");

        if (!patientRepo.findById(patientId).isPresent())
            throw new CustomException("Patient not found");

        if (!doctorRepo.findById(doctorId).get().getAvailableSlots().contains(slot))
            throw new CustomException("Slot not available for doctor");

        if (appointmentRepo.isSlotBooked(doctorId, slot))
            throw new CustomException("Slot already booked");

        return appointmentRepo.save(new Appointment(null, doctorId, patientId, slot));
    }

    public Collection<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepo.findByDoctorId(doctorId);
    }
}
