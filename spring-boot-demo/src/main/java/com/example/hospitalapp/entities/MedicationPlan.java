package com.example.hospitalapp.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "medicationplan")
public class MedicationPlan {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "hour_start")
    private Integer hourStart;

    @Column(name = "hour_end")
    private Integer hourEnd;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="medication_id")
    private Medication medication;

    public MedicationPlan(Integer number, Integer hourStart, Integer hourEnd, Patient patient, Medication medication) {
        this.number = number;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.patient = patient;
        this.medication = medication;
    }

    public MedicationPlan(Integer id, Integer number, Integer hourStart, Integer hourEnd, Patient patient, Medication medications) {
        this.id=id;
        this.number = number;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.patient = patient;
        this.medication = medications;
    }

    public MedicationPlan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getHourStart() {
        return hourStart;
    }

    public void setHourStart(Integer hourStart) {
        this.hourStart = hourStart;
    }

    public Integer getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(Integer hourEnd) {
        this.hourEnd = hourEnd;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medications) {
        this.medication = medications;
    }
}
