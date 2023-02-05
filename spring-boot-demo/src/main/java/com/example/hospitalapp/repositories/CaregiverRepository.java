package com.example.hospitalapp.repositories;

import com.example.hospitalapp.dto.CaregiverDTO;
import com.example.hospitalapp.entities.Caregiver;
import com.example.hospitalapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CaregiverRepository extends JpaRepository<Caregiver, Integer> {

    @Query(value = "SELECT u " +
            "FROM Caregiver u " +
            "ORDER BY u.name")
    Set<Caregiver> getAllOrdered();

    @Query(value = "SELECT p " +
            "FROM Caregiver p " +
            "INNER JOIN FETCH p.patientsList i"
    )
    Set<Caregiver> getAllFetch();

    @Query(value = "SELECT d " +
            "FROM Caregiver d " +
            "WHERE d.email = ?1 AND d.password = ?2 "
    )
    Caregiver loginCaregiver(String email, String password);

    @Query(value = "SELECT d " +
            "FROM Caregiver d " +
            "WHERE d.id = ?1 "
    )
    Caregiver myFindById(Integer id);
}
