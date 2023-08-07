package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.entities.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

}
