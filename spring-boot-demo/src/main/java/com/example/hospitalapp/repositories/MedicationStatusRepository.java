package com.example.hospitalapp.repositories;

import com.example.hospitalapp.entities.MedicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationStatusRepository extends JpaRepository<MedicationStatus, Integer> {
}
