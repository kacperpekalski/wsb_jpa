package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PatientDaoImplTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    void findPatientsByLastName() {
        // given
        String lastName = "Wroclawska";

        // when
        List<PatientEntity> patients = patientDao.findPatientsByLastName(lastName);

        // then
        assertThat(patients).isNotEmpty();
        assertThat(patients.size()).isEqualTo(2);
        assertThat(patients.get(0).getLastName()).isEqualTo(lastName);
        assertThat(patients.get(1).getLastName()).isEqualTo(lastName);
    }

}