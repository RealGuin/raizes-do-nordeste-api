package com.raizesdonordeste.raizesnovoapi.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raizesdonordeste.raizesnovoapi.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}