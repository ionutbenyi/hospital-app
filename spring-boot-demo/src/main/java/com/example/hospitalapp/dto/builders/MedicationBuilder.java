package com.example.hospitalapp.dto.builders;

import com.example.hospitalapp.dto.MedicationDTO;
import com.example.hospitalapp.entities.Medication;

public class MedicationBuilder {

    public MedicationBuilder() {
    }

    public static MedicationDTO generateDTOFromEntity(Medication m){
        return new MedicationDTO(m.getId(),m.getName(),m.getSideEffects(),m.getPlansList());
    }

    public static Medication generateEntityFromDTO(MedicationDTO m){
        return new Medication(m.getId(), m.getName(),m.getSideEffects(),m.getPlansList());
    }

}
