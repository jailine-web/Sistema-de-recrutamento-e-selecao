package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.model.entities.Login;
import com.example.app.model.entities.Recrutador;

@Repository
public interface LoginRepository  extends JpaRepository<Login, Integer>{

	@Query("Select email from Recrutador r where r.email= :email")
	List<Recrutador>findByEmail(String email);
}
