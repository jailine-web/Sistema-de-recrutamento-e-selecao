package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.entities.Entrevista;

@Repository
public interface EntrevistaRepository extends JpaRepository<Entrevista, Integer>{

}
