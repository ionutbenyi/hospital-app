package com.example.hospitalapp.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor")
public class Doctor extends User{



    //Doctor == the owner side of the many-to-many
    @ManyToMany
    @JoinTable(
            name = "doctor_has_patient",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Set<Patient> patientsList;

    public Set<Patient> getPatientsList() {
        return patientsList;
    }

    public Doctor(String name, String email, String password, Set<Patient> patientsList) {
        super(name, email, password);
        this.patientsList = patientsList;
    }

    public Doctor(String name, String email, String password) {
        super(name, email, password);
        this.patientsList = new HashSet<Patient>();
    }

    public Doctor(Integer id, String name, String email, String password, Set<Patient> patientsList) {
        super(id, name, email, password);
        this.patientsList = patientsList;
    }

    public Doctor(Integer id, String name, String email, String password) {
        super(id, name, email, password);
        this.patientsList = new HashSet<Patient>();
    }

    public Doctor(){

    }

    public void addPatient(Patient p){
        this.patientsList.add(p);
    }

    public void removePatient(Patient p){
        this.patientsList.remove(p);
    }
}
