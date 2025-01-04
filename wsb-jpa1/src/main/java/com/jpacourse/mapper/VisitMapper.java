package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;

import java.util.List;
import java.util.stream.Collectors;

public final class VisitMapper {

    private VisitMapper() {}

    public static VisitTO mapToTO(VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorFullName(visitEntity.getDoctor().getFirstName() + " " + visitEntity.getDoctor().getLastName());

        // mapowanie listy typów z MedicalTreatment
        List<String> medicalTreatmentTypes = visitEntity.getVisits().stream()
                .map(MedicalTreatmentEntity::getType)
                .map(TreatmentType::name)
                .collect(Collectors.toList());
        visitTO.setMedicalTreatmentTypes(medicalTreatmentTypes);
        return visitTO;
    }

    public static List<VisitTO> mapVisitsToTOs(List<VisitEntity> visits) {
        return visits.stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }
}