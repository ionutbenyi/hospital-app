package com.example.hospitalapp.dto;

import com.example.hospitalapp.entities.Medication;
import com.example.hospitalapp.entities.Patient;

import java.util.Objects;
import java.util.Set;

public class MedicationPlanDTO {
    private Integer id;
    private Integer number;
    private Integer hourStart;
    private Integer hourEnd;
    private Patient patient;
    private Medication medication;

    public MedicationPlanDTO(Integer id, Integer number, Integer hourStart, Integer hourEnd, Patient patient, Medication medications) {
        this.id = id;
        this.number = number;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.patient = patient;
        this.medication = medications;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedications(Medication medications) {
        this.medication = medications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationPlanDTO mpDTO = (MedicationPlanDTO) o;
        return Objects.equals(id, mpDTO.id) &&
                Objects.equals(number, mpDTO.number) &&
                Objects.equals(hourStart, mpDTO.hourStart) &&
                Objects.equals(hourEnd, mpDTO.hourEnd) &&
                Objects.equals(patient, mpDTO.patient) &&
                Objects.equals(medication, mpDTO.medication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, hourStart, hourEnd, patient, medication);
    }
}
