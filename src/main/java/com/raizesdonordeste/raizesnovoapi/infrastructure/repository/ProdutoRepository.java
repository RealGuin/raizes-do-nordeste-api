package com.raizesdonordeste.raizesnovoapi.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raizesdonordeste.raizesnovoapi.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
