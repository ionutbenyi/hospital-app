package com.example.hospitalapp.repositories;

import com.example.hospitalapp.entities.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}
