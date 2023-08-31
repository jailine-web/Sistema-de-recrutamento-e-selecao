package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.entities.Lembrete;

public interface LembreteRepository extends JpaRepository<Lembrete, Integer>{

}
