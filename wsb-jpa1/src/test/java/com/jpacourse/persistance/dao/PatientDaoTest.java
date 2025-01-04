package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Test
    public void testShouldFindPatientById() {
        // given
        // when
        PatientEntity patientEntity = patientDao.findOne(3L);

        // then
        assertThat(patientEntity).isNotNull();
        assertThat(patientEntity.getAge()).isEqualTo(99);
    }

    @Transactional
    @Test
    public void testShouldSavePatient() {
        // given
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("Test");
        patientEntity.setLastName("Testowy");
        patientEntity.setTelephoneNumber("111222333");
        patientEntity.setEmail("test.testowy@gmail.com");
        patientEntity.setPatientNumber("P999");
        patientEntity.setDateOfBirth(LocalDate.of(1992, 6, 11));
        patientEntity.setAge(31);

        long entitiesNumBefore = patientDao.count();

        // when
        final PatientEntity saved = patientDao.save(patientEntity);

        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(patientDao.count()).isEqualTo(entitiesNumBefore + 1);
    }

    @Transactional
    @Test
    public void testShouldSaveAndRemovePatient() {
        // given
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("Daniel");
        patientEntity.setLastName("Kowalski");
        patientEntity.setTelephoneNumber("987654321");
        patientEntity.setEmail("daniel.kowalski@yahoo.com");
        patientEntity.setPatientNumber("P112233");
        patientEntity.setDateOfBirth(LocalDate.of(1999, 5, 10));
        patientEntity.setAge(25);

        // when
        final PatientEntity saved = patientDao.save(patientEntity);
        assertThat(saved.getId()).isNotNull();
        final PatientEntity newSaved = patientDao.findOne(saved.getId());
        assertThat(newSaved).isNotNull();

        patientDao.delete(saved.getId());

        // then
        final PatientEntity removed = patientDao.findOne(saved.getId());
        assertThat(removed).isNull();
    }

    /*@Test
    @Transactional
    public void testAddNewVisit() {
        // given
        Long patientId = 1L;
        Long doctorId = 1L;
        LocalDateTime visitDate = LocalDateTime.of(2025, 1, 2, 10, 0);
        String description = "Test visit";

        // when
        VisitEntity newVisit = patientDao.addNewVisit(patientId, doctorId, visitDate, description);

        // then
        assertThat(newVisit).isNotNull();
        assertThat(newVisit.getId()).isNotNull();
        assertThat(newVisit.getPatient().getId()).isEqualTo(patientId);
        assertThat(newVisit.getDoctor().getId()).isEqualTo(doctorId);
        assertThat(newVisit.getTime()).isEqualTo(visitDate);
        assertThat(newVisit.getDescription()).isEqualTo(description);
    }*/

    @Test
    @Transactional
    public void testAddNewVisit() {
        // given
        Long patientId = 2L;
        Long doctorId = 3L;
        LocalDateTime date = LocalDateTime.of(2025, 1, 18, 9, 15);
        String visitDescription = "wizyta testowa";
        String treatmentDescription = "opis testowy";
        TreatmentType treatmentType = TreatmentType.EKG;

        // when
        VisitEntity newVisit = patientDao.addNewVisit(patientId, doctorId, date, visitDescription, treatmentDescription, treatmentType);

        // then
        assertThat(newVisit).isNotNull();
        assertThat(newVisit.getDescription()).isEqualTo(visitDescription);
        assertThat(newVisit.getPatient().getId()).isEqualTo(patientId);
        assertThat(newVisit.getDoctor().getId()).isEqualTo(doctorId);

        // Dodatkowe asercje dla MedicalTreatmentEntity
        assertThat(newVisit.getVisits().get(0).getDescription()).isEqualTo(treatmentDescription);
        assertThat(newVisit.getVisits().get(0).getType()).isEqualTo(treatmentType);
    }

}