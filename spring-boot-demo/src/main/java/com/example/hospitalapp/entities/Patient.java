package com.example.hospitalapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient extends User{

    @ManyToMany(mappedBy = "patientsList", fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    private Set<Doctor> doctors;

    @JsonIgnore
    @OneToMany(mappedBy="patient")
    private Set<MedicationPlan> medicationPlans;

    @JsonIgnore
    @ManyToMany(mappedBy = "patientsList", fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    private Set<Caregiver> caregivers;

    public Patient(String name, String email, String password, Set<Doctor> doctors, Set<MedicationPlan> medicationPlans, Set<Caregiver> caregivers) {
        super(name, email, password);
        this.doctors = doctors;
        this.medicationPlans = medicationPlans;
        this.caregivers = caregivers;
    }

    public Patient(Integer id, String name, String email, String password, Set<Doctor> doctors, Set<MedicationPlan> medicationPlans, Set<Caregiver> caregivers) {
        super(id, name, email, password);
        this.doctors = doctors;
        this.medicationPlans = medicationPlans;
        this.caregivers = caregivers;
    }

    public Patient(String name, String email, String password) {
        super(name, email, password);
        this.doctors = new HashSet<Doctor>();
        this.medicationPlans = new HashSet<MedicationPlan>();
        this.caregivers = new HashSet<Caregiver>();
    }

    public Patient(String name, String email, String password, Set<Doctor> doctors) {
        super(name, email, password);
        this.doctors = doctors;
        this.medicationPlans = new HashSet<MedicationPlan>();
        this.caregivers = new HashSet<Caregiver>();
    }

    public Patient(String name, String email, String password, Set<Doctor> doctors, Set<Caregiver> caregivers) {
        super(name, email, password);
        this.doctors = doctors;
        this.medicationPlans = new HashSet<MedicationPlan>();
        this.caregivers = caregivers;
    }

    public Patient() {
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

    public void addMedicationPlan(MedicationPlan mp){
        this.medicationPlans.add(mp);
    }

    public void removeMedicationPlan(MedicationPlan mp){
        this.medicationPlans.remove(mp);
    }

    public void addDoctor(Doctor d){
        this.doctors.add(d);
    }

    public void removeDoctor(Doctor d){
        this.doctors.remove(d);
    }

    public Set<Caregiver> getCaregivers() {
        return caregivers;
    }

    public void setCaregivers(Set<Caregiver> caregivers) {
        this.caregivers = caregivers;
    }
}
