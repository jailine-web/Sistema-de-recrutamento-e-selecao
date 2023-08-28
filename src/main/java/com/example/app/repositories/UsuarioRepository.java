package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.app.model.entities.Login;

public interface UsuarioRepository extends JpaRepository<Login, Integer>{

    UserDetails findByUsuario(String usuario);
}
