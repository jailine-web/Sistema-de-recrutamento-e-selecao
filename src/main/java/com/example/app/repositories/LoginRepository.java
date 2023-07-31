package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
=======
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82

import com.example.app.model.entities.Login;
import com.example.app.model.entities.Recrutador;

public interface LoginRepository  extends JpaRepository<Login, Integer>{

	@Query("Select email from Recrutador r where r.email= :email")
	List<Recrutador>findByEmail(String email);
}
