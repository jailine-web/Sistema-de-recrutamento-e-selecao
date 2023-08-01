package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.entities.Candidatura;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

}
