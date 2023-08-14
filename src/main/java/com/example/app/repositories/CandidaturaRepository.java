package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.app.model.entities.Candidatura;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
	List<Candidatura> findByCandidatoLocalizacao(@Param("localizacao") String localizacao);
}
