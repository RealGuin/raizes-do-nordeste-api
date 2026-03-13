package com.raizesdonordeste.raizesnovoapi.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raizesdonordeste.raizesnovoapi.domain.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
	
	boolean existsByNome(String nome);

}
