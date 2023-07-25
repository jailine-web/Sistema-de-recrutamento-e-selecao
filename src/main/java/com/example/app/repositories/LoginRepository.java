package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.entities.Login;

@Repository
public interface LoginRepository  extends JpaRepository<Login, Integer>{

}
