package com.example.hospitalapp.dto.builders;

import com.example.hospitalapp.dto.CaregiverViewDTO;
import com.example.hospitalapp.entities.Caregiver;

public class CaregiverViewBuilder {

    public static CaregiverViewDTO generateDTOFromEntity(Caregiver c){
        return new CaregiverViewDTO(c.getId(),c.getName(),c.getEmail(),c.isAvailable(),c.getPatientsList());
    }

    public static Caregiver generateEntityFromDTO(CaregiverViewDTO c, String pass){
        return new Caregiver(c.getId(),c.getName(),c.getEmail(),pass, c.isAvailable(),c.getPatientsList());
    }
}
