package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.entities.Recrutador;

public interface RecrutadorRepository extends JpaRepository<Recrutador, Integer> {
	
}
