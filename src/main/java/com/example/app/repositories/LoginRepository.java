package com.example.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.app.model.entities.Login;
import com.example.app.projection.LoginProjection;

import jakarta.transaction.Transactional;

@Repository
public interface LoginRepository  extends JpaRepository<Login, Integer>{

	@Transactional
	@Query (nativeQuery = true, value= """
			Select usuario, senha, email from Login where usuario = :usuario """)
	
	Optional<LoginProjection> buscarUsuarios(@Param("usuario") String usuario);
	
	@Transactional
	@Query (
			"Select email from Login where email= :email")
	
	Optional<LoginProjection> buscarEmail(@Param("email") String email);
	
	
}
