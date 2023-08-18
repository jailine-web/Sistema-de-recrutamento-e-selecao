package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.entities.Questionario;

public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

}
