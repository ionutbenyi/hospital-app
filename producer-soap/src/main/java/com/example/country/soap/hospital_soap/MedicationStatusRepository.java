package com.example.country.soap.hospital_soap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationStatusRepository extends JpaRepository<MedicationStatus, Integer> {

    @Query(value = "SELECT m " +
            "FROM MedicationStatus m " +
            "WHERE m.userId = ?1 "
    )
    List<MedicationStatus> findByUserId(Integer userId);
}