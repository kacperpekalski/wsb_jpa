package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Override
    public VisitEntity addNewVisit(Long patientId, Long doctorId, LocalDateTime date, String visitDescription, String treatmentDescription, TreatmentType treatmentType) {
        PatientEntity patientEntity = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctorEntity = entityManager.find(DoctorEntity.class, doctorId);

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setTime(date);
        visitEntity.setDescription(visitDescription);
        visitEntity.setDoctor(doctorEntity);
        visitEntity.setPatient(patientEntity);

        MedicalTreatmentEntity medicalTreatment = new MedicalTreatmentEntity();
        medicalTreatment.setDescription(treatmentDescription);
        medicalTreatment.setType(treatmentType);
        medicalTreatment.setVisit(visitEntity);

        visitEntity.getVisits().add(medicalTreatment);

        patientEntity.getVisits().add(visitEntity);

        update(patientEntity);

        return visitEntity;
    }


    @Override
    public List<PatientEntity> findPatientsByLastName(String last_name) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p " +
                        "WHERE lastName = :lastName", PatientEntity.class)
                .setParameter("lastName", last_name)
                .getResultList();
    }


    @Override
    public List<VisitEntity> findVisitsByPatientId(Long patient_Id) {
        return entityManager.createQuery("SELECT v FROM VisitEntity v " +
                        "JOIN FETCH v.patient p " +
                        "WHERE p.id = :patientId", VisitEntity.class)
                .setParameter("patientId", patient_Id)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(Integer x) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p " +
                        "JOIN FETCH p.visits v " +
                        "GROUP BY p " +
                        "HAVING COUNT(v) > :x", PatientEntity.class)
                .setParameter("x", x)
                .getResultList();
    }



    // znajdz pacjentów z wiekiem mniejszym niż podany (age to dodane pole z lab 2)
    @Override
    public List<PatientEntity> findPatientsByAgeLessThan(Integer age) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p " +
                        "WHERE p.age < :age", PatientEntity.class)
                .setParameter("age", age)
                .getResultList();
    }


    // znajdz pacjentów z wiekiem wiekszym niż podany (age to dodane pole z lab 2)
    @Override
    public List<PatientEntity> findPatientsByAgeGreaterThan(Integer age) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p " +
                        "WHERE p.age > :age", PatientEntity.class)
                .setParameter("age", age)
                .getResultList();
    }

}