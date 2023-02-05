package com.example.hospitalapp.dto;

import com.example.hospitalapp.entities.Patient;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DoctorDTO {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private Set<Patient> patientsList;

    public DoctorDTO(Integer id, String name, String email, String password, Set<Patient> patientsList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.patientsList = patientsList;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Patient> getPatientsList() {
        return patientsList;
    }

    public void setPatientsList(Set<Patient> patientsList) {
        this.patientsList = patientsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDTO docDTO = (DoctorDTO) o;
        return Objects.equals(id, docDTO.id) &&
                Objects.equals(name, docDTO.name) &&
                Objects.equals(email, docDTO.email) &&
                Objects.equals(password, docDTO.password) &&
                Objects.equals(patientsList, docDTO.patientsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, patientsList);
    }
}
