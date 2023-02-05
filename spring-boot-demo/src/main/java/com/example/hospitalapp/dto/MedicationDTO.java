package com.example.hospitalapp.dto;

import com.example.hospitalapp.entities.Medication;
import com.example.hospitalapp.entities.MedicationPlan;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MedicationDTO {

    private Integer id;
    private String name;
    private String sideEffects;
    private Set<MedicationPlan> plansList;

    public MedicationDTO(Integer id, String name, String sideEffects, Set<MedicationPlan> plansList) {
        this.id = id;
        this.name = name;
        this.sideEffects = sideEffects;
        this.plansList = plansList;
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

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Set<MedicationPlan> getPlansList() {
        return plansList;
    }

    public void setPlansList(Set<MedicationPlan> plansList) {
        this.plansList = plansList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationDTO mDTO = (MedicationDTO) o;
        return Objects.equals(id, mDTO.id) &&
                Objects.equals(name, mDTO.name) &&
                Objects.equals(sideEffects, mDTO.sideEffects) &&
                Objects.equals(plansList, mDTO.plansList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sideEffects, plansList);
    }
}
