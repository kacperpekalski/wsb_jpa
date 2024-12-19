package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;

import java.util.List;
import java.util.stream.Collectors;

public final class PatientMapper {

    private PatientMapper() {}

    public static PatientTO mapToTO(final PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setAge(patientEntity.getAge());
        patientTO.setVisits(mapVisitsToTOs(patientEntity.getVisits()));
        return patientTO;
    }

    private static List<VisitTO> mapVisitsToTOs(List<VisitEntity> visits) {
        return visits.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    private static VisitTO mapToTO(VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorFullName(visitEntity.getDoctor().getFirstName() + " " + visitEntity.getDoctor().getLastName());

        // mapowanie listy typów z MedicalTreatment
        List<String> medicalTreatmentTypes = visitEntity.getMedicalTreatment().getVisits().stream()
                .map(VisitEntity::getMedicalTreatment)
                .map(MedicalTreatmentEntity::getType)
                .map(TreatmentType::name)
                .collect(Collectors.toList());
        visitTO.setMedicalTreatmentTypes(medicalTreatmentTypes);
        return visitTO;
    }
}