package com.learningapp.repository;

import com.learningapp.data.entity.LearningParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearningPartyRepository extends JpaRepository<LearningParty, Long> {
    Optional<LearningParty> findByEmail(String email);
}
