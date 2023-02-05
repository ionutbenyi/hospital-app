package com.example.hospitalapp.repositories;

import com.example.hospitalapp.dto.PatientDTO;
import com.example.hospitalapp.entities.MedicationPlan;
import com.example.hospitalapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, Integer> {

    @Query(value = "SELECT u " +
            "FROM MedicationPlan u " +
            "ORDER BY u.number ")
    List<MedicationPlan> getAllOrdered();

    @Query(value = "SELECT d " +
            "FROM MedicationPlan d " +
            "WHERE d.patient.id = ?1 "
    )
    List<MedicationPlan> findByUserId(Integer id);

    @Query(value = "SELECT d " +
            "FROM MedicationPlan d " +
            "WHERE d.number = ?1 "
    )
    MedicationPlan getByNumber(Integer id);

    @Query(value = "SELECT d.patient " +
            "FROM MedicationPlan d "
    )
    List<Patient> getMedplansPatients();
}
