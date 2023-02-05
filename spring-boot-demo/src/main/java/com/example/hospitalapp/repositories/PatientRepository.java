package com.example.hospitalapp.repositories;

import com.example.hospitalapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "SELECT p " +
            "FROM Patient p " +
            "ORDER BY p.name")
    Set<Patient> getAllOrdered();

    @Query(value = "SELECT p " +
            "FROM Patient p " +
            "INNER JOIN FETCH p.doctors i "+
            "INNER JOIN FETCH p.medicationPlans i "+
            "INNER JOIN FETCH p.caregivers i"
    )
    Set<Patient> getAllFetch();

    @Query(value = "SELECT d " +
            "FROM Patient d " +
            "WHERE d.email = ?1 AND d.password = ?2 "
    )
    Patient loginPatient(String email, String password);
}
