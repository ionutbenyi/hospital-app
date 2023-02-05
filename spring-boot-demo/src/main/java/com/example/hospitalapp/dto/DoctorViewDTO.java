package com.example.hospitalapp.dto;

import com.example.hospitalapp.entities.Patient;

import java.util.Set;

public class DoctorViewDTO {

    private String name;
    private String email;
    private Set<Patient> patientsList;

    public DoctorViewDTO(String name, String email, Set<Patient> patientsList) {
        this.name = name;
        this.email = email;
        this.patientsList = patientsList;
    }

    public DoctorViewDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Patient> getPatientsList() {
        return patientsList;
    }

    public void setPatientsList(Set<Patient> patientsList) {
        this.patientsList = patientsList;
    }


}
