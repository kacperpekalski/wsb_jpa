package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public VisitEntity addNewVisit(Long patientId, Long doctorId, LocalDateTime date, String description) {
        PatientEntity patientEntity = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctorEntity = entityManager.find(DoctorEntity.class, doctorId);

        MedicalTreatmentEntity medicalTreatment = new MedicalTreatmentEntity();
        medicalTreatment.setDescription("Test description");
        medicalTreatment.setType(TreatmentType.EKG);
        entityManager.persist(medicalTreatment);

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setTime(date);
        visitEntity.setDescription(description);
        visitEntity.setDoctor(doctorEntity);
        visitEntity.setPatient(patientEntity);

        patientEntity.getVisits().add(visitEntity);
        entityManager.merge(patientEntity);

        return visitEntity;
    }
}