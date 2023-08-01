package com.example.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.app.model.entities.Login;

public interface LoginRepository  extends JpaRepository<Login, Integer>{

	@Query("Select usuario from Login l where l.usuario= :usuario")
	Optional<Login> findByUsuario(String usuario);
}
