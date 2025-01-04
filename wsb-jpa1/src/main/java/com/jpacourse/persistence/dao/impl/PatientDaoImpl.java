package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

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

        //entityManager.persist(visitEntity);
        //entityManager.persist(medicalTreatment);

        patientEntity.getVisits().add(visitEntity);

        //tutaj update zamiast merge
        update(patientEntity);

        return visitEntity;
    }


}