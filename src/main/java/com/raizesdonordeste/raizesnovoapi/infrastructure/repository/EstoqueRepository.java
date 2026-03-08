package com.raizesdonordeste.raizesnovoapi.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raizesdonordeste.raizesnovoapi.domain.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	
	Estoque findByUnidadeIdAndProdutoId(Long unidadeId, Long produtoId);

}
