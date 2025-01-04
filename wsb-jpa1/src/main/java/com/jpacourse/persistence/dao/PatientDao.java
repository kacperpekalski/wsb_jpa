package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;

import java.time.LocalDateTime;

public interface PatientDao extends Dao<PatientEntity, Long> {

    VisitEntity addNewVisit(Long patientId, Long doctorId, LocalDateTime date, String visitDescription,
                            String treatmentDescription, TreatmentType treatmentType);
}