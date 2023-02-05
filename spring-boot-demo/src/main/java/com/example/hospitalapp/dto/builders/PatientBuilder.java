package com.example.hospitalapp.dto.builders;

import com.example.hospitalapp.dto.PatientDTO;
import com.example.hospitalapp.entities.Patient;

public class PatientBuilder {

    public PatientBuilder() {
    }

    public static PatientDTO generateDTOFromEntity(Patient p){
        return new PatientDTO(p.getId(),p.getName(),p.getEmail(),p.getPassword(),p.getDoctors(),p.getMedicationPlans(), p.getCaregivers());
    }

    public static Patient generateEntityFromDTO(PatientDTO p){
        return new Patient(p.getId(), p.getName(),p.getEmail(),p.getPassword(),p.getDoctors(),p.getMedicationPlans(), p.getCaregivers());
    }
}
