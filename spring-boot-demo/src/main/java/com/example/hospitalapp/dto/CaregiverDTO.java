package com.example.hospitalapp.dto;

import com.example.hospitalapp.entities.Caregiver;
import com.example.hospitalapp.entities.Patient;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CaregiverDTO {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private boolean available;
    private Set<Patient> patientsList;

    public CaregiverDTO(Integer id, String name, String email, String password, boolean available, Set<Patient> patientsList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.available = available;
        this.patientsList = patientsList;
    }

    public CaregiverDTO(String name, String email, String password, boolean available) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.available = true;
        this.patientsList = new HashSet<Patient>();
    }

    public CaregiverDTO(Integer id, String name, String email) {
        this.id=id;
        this.name = name;
        this.email = email;
    }

    public CaregiverDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverDTO cDTO = (CaregiverDTO) o;
        return Objects.equals(id, cDTO.id) &&
                Objects.equals(name, cDTO.name) &&
                Objects.equals(email, cDTO.email) &&
                Objects.equals(password, cDTO.password) &&
                Objects.equals(available, cDTO.available) &&
                Objects.equals(patientsList, cDTO.patientsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, available, patientsList);
    }
}
