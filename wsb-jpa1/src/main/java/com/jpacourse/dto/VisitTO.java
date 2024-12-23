package com.jpacourse.dto;

import java.time.LocalDateTime;
import java.util.List;

public class VisitTO {

    private Long id;
    private String description;
    private LocalDateTime time;
    private String doctorFullName;
    private List<String> medicalTreatmentTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public List<String> getMedicalTreatmentTypes() {
        return medicalTreatmentTypes;
    }

    public void setMedicalTreatmentTypes(List<String> medicalTreatmentTypes) {
        this.medicalTreatmentTypes = medicalTreatmentTypes;
    }
}