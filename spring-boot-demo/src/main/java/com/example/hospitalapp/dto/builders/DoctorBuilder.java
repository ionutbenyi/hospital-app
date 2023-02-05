package com.example.hospitalapp.dto.builders;

import com.example.hospitalapp.dto.DoctorDTO;
import com.example.hospitalapp.entities.Doctor;

public class DoctorBuilder {

    public DoctorBuilder() {
    }

    public static DoctorDTO generateDTOFromEntity(Doctor d){
        return new DoctorDTO(d.getId(), d.getName(), d.getEmail(), d.getPassword(), d.getPatientsList());
    }

    public static Doctor generateEntityFromDTO(DoctorDTO dt){
        return new Doctor(dt.getId(), dt.getName(),dt.getEmail(),dt.getPassword(),dt.getPatientsList());
    }
}
