package com.clinic.model;



import java.time.LocalDateTime;
import java.util.List;


public class Doctor {
    private Long id;
    private String name;
    private String specialization;
    private List<LocalDateTime> availableSlots;

    public Doctor(Long id, String name, String specialization, List<LocalDateTime> availableSlots) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.availableSlots = availableSlots;
    }

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<LocalDateTime> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<LocalDateTime> availableSlots) {
        this.availableSlots = availableSlots;
    }
}