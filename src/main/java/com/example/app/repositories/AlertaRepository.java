package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.entities.Alerta;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

}
