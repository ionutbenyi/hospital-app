package com.example.hospitalapp.dto.builders;

import com.example.hospitalapp.dto.DoctorViewDTO;
import com.example.hospitalapp.entities.Doctor;

public class DoctorViewBuilder {

    public DoctorViewBuilder() {
    }

    public static DoctorViewDTO generateDTOFromEntity(Doctor doc){
        return new DoctorViewDTO(doc.getName(), doc.getEmail(), doc.getPatientsList());
    }

    public static Doctor generateEntityFromDTO(DoctorViewDTO d, String pass){
        return new Doctor(d.getName(),d.getEmail(), pass, d.getPatientsList());
    }
}
