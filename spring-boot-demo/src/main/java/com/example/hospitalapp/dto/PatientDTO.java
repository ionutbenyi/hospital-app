package com.example.hospitalapp.dto;

import com.example.hospitalapp.entities.Caregiver;
import com.example.hospitalapp.entities.Doctor;
import com.example.hospitalapp.entities.MedicationPlan;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PatientDTO {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private Set<Doctor> doctors;
    private Set<MedicationPlan> medicationPlans;
    private Set<Caregiver> caregivers;

    public PatientDTO(Integer id, String name, String email, String password, Set<Doctor> doctors, Set<MedicationPlan> medicationPlans, Set<Caregiver> caregivers) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.doctors = doctors;
        this.medicationPlans = medicationPlans;
        this.caregivers = caregivers;
    }

    public PatientDTO(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.doctors = new HashSet<Doctor>();
        this.medicationPlans = new HashSet<MedicationPlan>();
        this.caregivers = new HashSet<Caregiver>();
    }

    public PatientDTO() {
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

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<MedicationPlan> getMedicationPlans() {
        return medicationPlans;
    }

    public void setMedicationPlans(Set<MedicationPlan> medicationPlans) {
        this.medicationPlans = medicationPlans;
    }

    public Set<Caregiver> getCaregivers() {
        return caregivers;
    }

    public void setCaregivers(Set<Caregiver> caregivers) {
        this.caregivers = caregivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO patientDTO = (PatientDTO) o;
        return Objects.equals(id, patientDTO.id) &&
                Objects.equals(name, patientDTO.name) &&
                Objects.equals(email, patientDTO.email) &&
                Objects.equals(password, patientDTO.password) &&
                Objects.equals(doctors, patientDTO.doctors) &&
                Objects.equals(medicationPlans, patientDTO.medicationPlans) &&
                Objects.equals(caregivers, patientDTO.caregivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, doctors, medicationPlans, caregivers);
    }
}
