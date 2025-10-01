package br.com.alura.comex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.alura.comex.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    UserDetails findByEmail(String email);   
    boolean existsByEmail(String email); 
} 
