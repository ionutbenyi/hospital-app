package com.example.hospitalapp.dto;

import com.example.hospitalapp.entities.Patient;

import java.util.Set;

public class CaregiverViewDTO {

    private Integer id;
    private String name;
    private String email;
    private boolean available;
    private Set<Patient> patientsList;

    public CaregiverViewDTO(Integer id, String name, String email, boolean available, Set<Patient> patientsList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.available = available;
        this.patientsList = patientsList;
    }

    public CaregiverViewDTO(String name, String email, boolean available, Set<Patient> patientsList) {
        this.name = name;
        this.email = email;
        this.available = available;
        this.patientsList = patientsList;
    }

    public CaregiverViewDTO(Integer id, String name, String email) {
        this.id=id;
        this.name = name;
        this.email = email;
    }

    public CaregiverViewDTO() {
    }

    public Integer getId() {
        return id;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<Patient> getPatientsList() {
        return patientsList;
    }

    public void setPatientsList(Set<Patient> patientsList) {
        this.patientsList = patientsList;
    }
}
