package com.example.hospitalapp.repositories;

import com.example.hospitalapp.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {

    @Query(value = "SELECT u " +
            "FROM Medication u " +
            "ORDER BY u.name")
    List<Medication> getAllOrdered();

    @Query(value = "SELECT d " +
            "FROM Medication d " +
            "WHERE d.name = ?1 AND d.sideEffects = ?2 "
    )
    Medication findMedication(String name, String sideEffects);
}
