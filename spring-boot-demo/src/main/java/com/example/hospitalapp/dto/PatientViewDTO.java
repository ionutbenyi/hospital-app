package com.example.hospitalapp.dto;

import com.example.hospitalapp.entities.Caregiver;
import com.example.hospitalapp.entities.Doctor;
import com.example.hospitalapp.entities.MedicationPlan;

import java.util.Set;

public class PatientViewDTO {
    private Integer id;
    private String name;
    private String email;
    private Set<Doctor> doctors;
    private Set<MedicationPlan> medicationPlans;
    private Set<Caregiver> caregivers;

    public PatientViewDTO(Integer id,String name, String email, Set<Doctor> doctors, Set<MedicationPlan> medicationPlans, Set<Caregiver> caregivers) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.doctors = doctors;
        this.medicationPlans = medicationPlans;
        this.caregivers = caregivers;
    }

    public PatientViewDTO() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
