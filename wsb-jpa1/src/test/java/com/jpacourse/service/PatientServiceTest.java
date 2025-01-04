package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;

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
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("Test");
        patientEntity.setLastName("Testowski");
        patientEntity.setAge(30);
        patientEntity.setTelephoneNumber("123456789");
        patientEntity.setEmail("test.testowski@aaa.com");
        patientEntity.setPatientNumber("P112211");
        patientEntity.setDateOfBirth(LocalDate.of(1993, 2, 11));
        patientEntity.setVisits(Collections.emptyList());

        patientEntity = patientDao.save(patientEntity);
        Long patientId = patientEntity.getId();

        PatientTO result = patientService.findById(patientId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(patientId);
        assertThat(result.getFirstName()).isEqualTo("Test");
        assertThat(result.getLastName()).isEqualTo("Testowski");
        assertThat(result.getAge()).isEqualTo(30);
        assertThat(result.getTelephoneNumber()).isEqualTo("123456789");
        assertThat(result.getEmail()).isEqualTo("test.testowski@aaa.com");
        assertThat(result.getPatientNumber()).isEqualTo("P112211");
        assertThat(result.getDateOfBirth()).isEqualTo(LocalDate.of(1993, 2, 11));
        assertThat(result.getVisits()).isEmpty();

        System.out.println("PatientTO: ");
        System.out.println("ID: " + result.getId());
        System.out.println("First Name: " + result.getFirstName());
    }

    @Test
    @Transactional
    public void testFindByIdShouldReturnNullIfPatientNotFound() {
        Long patientId = 999L;

        PatientTO result = patientService.findById(patientId);

        assertThat(result).isNull();
    }
}