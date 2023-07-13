package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.entities.Login;

public interface LoginRepository  extends JpaRepository<Login, Integer>{

}
