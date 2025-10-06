package br.com.alura.comex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.model.UsuarioCache;

public interface UsuarioCacheRepository extends JpaRepository<UsuarioCache, Long>{
    Optional<UsuarioCache> findByEmail(String email);
}