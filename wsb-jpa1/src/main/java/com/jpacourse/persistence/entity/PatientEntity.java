package com.jpacourse.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {

	@Version
	@Column(nullable = true)
	private Integer version;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String telephoneNumber;

	@Column // nullable = true
	private String email;

	@Column(nullable = false)
	private String patientNumber;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Column(nullable = false)
	private Integer age; // pole z lab 2

	// Relacja dwustronna od strony rodzica (właściciela relacji): PatientEntity (ta encja)
	// Strona dziecka: AddressEntity
	// Relacja jeden do jeden PatientEntity z AddressEntity, gdzie jeden pacjent ma jeden adres
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressEntity address;

	// Relacja dwustronna od strony rodzica: PatientEntity
	// Strona dziecka: VisitEntity
	// Relacja jeden do wielu, gdzie jeden PatientEntity ma wiele rekordów w VisitEntity
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<VisitEntity> visits = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<VisitEntity> getVisits() {
		return visits;
	}

	public void setVisits(List<VisitEntity> visits) {
		this.visits = visits;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}