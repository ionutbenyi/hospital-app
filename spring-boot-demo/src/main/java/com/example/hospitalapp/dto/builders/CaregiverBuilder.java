package com.example.hospitalapp.dto.builders;

import com.example.hospitalapp.dto.CaregiverDTO;
import com.example.hospitalapp.entities.Caregiver;

public class CaregiverBuilder {

    public CaregiverBuilder() {
    }

    public static CaregiverDTO generateDTOFromEntity(Caregiver c){
        return new CaregiverDTO(c.getId(),c.getName(),c.getEmail(),c.getPassword(),c.isAvailable(),c.getPatientsList());
    }

    public static Caregiver generateEntityFromDTO(CaregiverDTO c){
        return new Caregiver(c.getId(),c.getName(),c.getEmail(),c.getPassword(), c.isAvailable(), c.getPatientsList());
    }
}
