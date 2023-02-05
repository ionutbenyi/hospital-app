package com.example.hospitalapp.repositories;

import com.example.hospitalapp.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(value = "SELECT u " +
            "FROM Person u " +
            "ORDER BY u.name")
    List<Person> getAllOrdered();
}
