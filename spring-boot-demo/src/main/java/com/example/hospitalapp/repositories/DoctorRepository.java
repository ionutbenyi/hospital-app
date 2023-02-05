package com.example.hospitalapp.repositories;

import com.example.hospitalapp.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value = "SELECT u " +
            "FROM Doctor u " +
            "ORDER BY u.name")
    List<Doctor> getAllOrdered();

    @Query(value = "SELECT d " +
            "FROM Doctor d " +
            "WHERE d.email = ?1 AND d.password = ?2 "
    )
    Doctor loginDoctor(String email, String password);
}
