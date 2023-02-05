package com.example.hospitalapp.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "medication_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "side_effects", length = 500)
    private String sideEffects;

    @ManyToMany
    @JoinTable(
            name = "medication_has_medicationplan",
            joinColumns = @JoinColumn(name = "medication_id"),
            inverseJoinColumns = @JoinColumn(name = "medicationplan_id"))
    private Set<MedicationPlan> plansList;


    public Medication(Integer id, String name, String sideEffects, Set<MedicationPlan> plansList) {
        this.id=id;
        this.name = name;
        this.sideEffects = sideEffects;
        this.plansList = plansList;
    }

    public Medication() {
    }

    public String getName() {
        return name;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public Set<MedicationPlan> getPlansList() {
        return plansList;
    }

    public void addMedicationPlan(MedicationPlan mp){
        this.plansList.add(mp);
    }

    public void removeMedicationPlan(MedicationPlan mp){
        this.plansList.remove(mp);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
