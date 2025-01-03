package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    @Mock
    private PatientDao patientDao;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByIdShouldReturnPatientTO() {
        Long patientId = 1L;

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientId);
        patientEntity.setFirstName("John");
        patientEntity.setLastName("Doe");
        patientEntity.setAge(30);
        patientEntity.setTelephoneNumber("123456789");
        patientEntity.setEmail("john.doe@example.com");
        patientEntity.setPatientNumber("P12345");
        patientEntity.setDateOfBirth(LocalDate.of(1993, 5, 20));
        patientEntity.setVisits(Collections.emptyList());

        when(patientDao.findOne(patientId)).thenReturn(patientEntity);

        PatientTO result = patientService.findById(patientId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(patientId);
        assertThat(result.getFirstName()).isEqualTo("John");
        assertThat(result.getLastName()).isEqualTo("Doe");
        assertThat(result.getAge()).isEqualTo(30);
        assertThat(result.getTelephoneNumber()).isEqualTo("123456789");
        assertThat(result.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(result.getPatientNumber()).isEqualTo("P12345");
        assertThat(result.getDateOfBirth()).isEqualTo(LocalDate.of(1993, 5, 20));
        assertThat(result.getVisits()).isEmpty();

        verify(patientDao, times(1)).findOne(patientId);
    }

    @Test
    public void testFindByIdShouldReturnNullIfPatientNotFound() {
        Long patientId = 1L;

        when(patientDao.findOne(patientId)).thenReturn(null);

        PatientTO result = patientService.findById(patientId);

        assertThat(result).isNull();

        verify(patientDao, times(1)).findOne(patientId);
    }
}
