package com.example.country.soap.hospital_soap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PatientStatusRepository extends JpaRepository<PatientStatus, Integer> {
    @Query(value = "SELECT p " +
            "FROM PatientStatus p " +
            "WHERE p.patientId = ?1 ")
    Set<PatientStatus> findByPatientId(Integer id);
}
