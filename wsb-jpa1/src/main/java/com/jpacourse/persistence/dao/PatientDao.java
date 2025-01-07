package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {

    VisitEntity addNewVisit(Long patientId, Long doctorId, LocalDateTime date, String visitDescription,
                            String treatmentDescription, TreatmentType treatmentType);

    List<PatientEntity> findPatientsByLastName(String lastName);

    List<VisitEntity> findVisitsByPatientId(Long patientId);

    List<PatientEntity> findPatientsWithMoreThanXVisits(Integer x);

    List<PatientEntity> findPatientsByAgeLessThan(Integer age);

    List<PatientEntity> findPatientsByAgeGreaterThan(Integer age);


}