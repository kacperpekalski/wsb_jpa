package com.jpacourse.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	// Relacja jednostronna od strony dziecka (VisitEntity) do rodzica (PatientEntity)
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private PatientEntity patient;

	// Relacja dwustronna (VisitEntity) z DoctorEntity
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private DoctorEntity doctor;

	// Relacja jednostronna od strony dziecka (VisitEntity) do rodzica (MedicalTreatmentEntity)
	@ManyToOne
	@JoinColumn(name = "medical_treatment_id", nullable = false)
	private MedicalTreatmentEntity medicalTreatment;


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

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public MedicalTreatmentEntity getMedicalTreatment() {
		return medicalTreatment;
	}

	public void setMedicalTreatment(MedicalTreatmentEntity medicalTreatment) {
		this.medicalTreatment = medicalTreatment;
	}
}