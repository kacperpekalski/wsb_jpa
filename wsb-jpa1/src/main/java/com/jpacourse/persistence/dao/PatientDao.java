package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;

public interface PatientDao extends Dao<PatientEntity, Long> {

    VisitEntity addNewVisit(Long patientId, Long doctorId, LocalDateTime date, String description);
}