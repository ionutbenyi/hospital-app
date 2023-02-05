package com.example.hospitalapp.repositories;

import com.example.hospitalapp.entities.PatientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PatientStatusRepository  extends JpaRepository<PatientStatus, Integer> {
    @Query(value = "SELECT p " +
            "FROM PatientStatus p " +
            "ORDER BY p.patientId")
    Set<PatientStatus> getAllOrdered();

    @Query(value = "SELECT p " +
            "FROM PatientStatus p " +
            "WHERE p.patientId = ?1 ")
    Set<PatientStatus> findByPatientId(Integer id);
}
