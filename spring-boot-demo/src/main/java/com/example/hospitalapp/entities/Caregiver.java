package com.example.hospitalapp.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "caregiver")
public class Caregiver extends User{

    private boolean available;

    //Caregiver == the owner side of the many-to-many
    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    @JoinTable(
            name = "caregiver_has_patient",
            joinColumns = @JoinColumn(name = "caregiver_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Set<Patient> patientsList;

    public Caregiver(String name, String email, String password) {
        super(name, email, password);
        this.available = true;
        this.patientsList = new HashSet<Patient>();
    }

    public Caregiver(Integer id, String name, String email, String password, boolean available, Set<Patient> patientsList) {
        super(id, name, email, password);
        this.available = available;
        this.patientsList = patientsList;
    }

    public Caregiver() {
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
