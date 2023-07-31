package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.entities.Candidatura;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Integer> {

}
