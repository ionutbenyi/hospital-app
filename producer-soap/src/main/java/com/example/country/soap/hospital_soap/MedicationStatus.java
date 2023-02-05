package com.example.country.soap.hospital_soap;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "medicationstatus")
public class MedicationStatus {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "taken")
    private Boolean taken;

    @Column(name = "medicationName")
    private String medicationName;

    public MedicationStatus(Integer userId, Boolean taken, String medicationName) {
        this.userId = userId;
        this.taken = taken;
        this.medicationName = medicationName;
    }

    public MedicationStatus() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Boolean getTaken() {
        return taken;
    }

    public String getMedicationName() {
        return medicationName;
    }
}