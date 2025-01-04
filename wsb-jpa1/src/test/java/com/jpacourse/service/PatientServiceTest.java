package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientDao patientDao;

    private PatientServiceImpl patientService;

    @BeforeEach
    public void setUp() {
        patientService = new PatientServiceImpl(patientDao);
    }

    @Test
    @Transactional
    public void testFindByIdShouldReturnPatientTO() {
        // given
        Long patientId = 1L; // ID pacjenta z data.sql

        // when
        PatientTO result = patientService.findById(patientId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(patientId);
        assertThat(result.getFirstName()).isEqualTo("Lukasz"); //dane również z data.sql
        assertThat(result.getLastName()).isEqualTo("Szkolarz");
        assertThat(result.getAge()).isEqualTo(44);
        assertThat(result.getTelephoneNumber()).isEqualTo("123123331");
        assertThat(result.getEmail()).isEqualTo("lukasz.szkolarz@kalafior.pl");
        assertThat(result.getPatientNumber()).isEqualTo("P420");
        assertThat(result.getDateOfBirth()).isEqualTo(LocalDate.of(1912, 2, 9));

        System.out.println("\nPatientTO:\n");
        System.out.println("ID: " + result.getId());
        System.out.println("First Name: " + result.getFirstName() + "\n");
    }

    @Test
    @Transactional
    public void testFindByIdShouldReturnNullIfPatientNotFound() {
        Long patientId = 999L;

        PatientTO result = patientService.findById(patientId);

        assertThat(result).isNull();
    }
}