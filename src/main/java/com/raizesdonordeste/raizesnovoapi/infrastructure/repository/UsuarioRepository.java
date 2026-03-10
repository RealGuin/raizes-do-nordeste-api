package com.raizesdonordeste.raizesnovoapi.infrastructure.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.raizesdonordeste.raizesnovoapi.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
	
	boolean existsByEmail(String email);

}