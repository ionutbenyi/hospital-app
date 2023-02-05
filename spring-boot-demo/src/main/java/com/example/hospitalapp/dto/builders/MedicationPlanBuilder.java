package com.example.hospitalapp.dto.builders;

import com.example.hospitalapp.dto.MedicationPlanDTO;
import com.example.hospitalapp.entities.Medication;
import com.example.hospitalapp.entities.MedicationPlan;

public class MedicationPlanBuilder {

    public MedicationPlanBuilder() {
    }

    public static MedicationPlanDTO generateDTOFromEntity(MedicationPlan mp){
        return new MedicationPlanDTO(mp.getId(),mp.getNumber(),mp.getHourStart(),mp.getHourEnd(),mp.getPatient(),mp.getMedication());
    }

    public static MedicationPlan generateEntityFromDTO(MedicationPlanDTO mp){
        return new MedicationPlan(mp.getId(), mp.getNumber(),mp.getHourStart(),mp.getHourEnd(),mp.getPatient(),mp.getMedication());
    }
}
