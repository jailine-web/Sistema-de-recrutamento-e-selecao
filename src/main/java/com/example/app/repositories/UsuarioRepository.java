package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.app.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    UserDetails findByUsuario(String usuario);
}
