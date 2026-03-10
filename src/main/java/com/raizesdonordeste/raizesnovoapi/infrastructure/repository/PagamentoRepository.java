package com.raizesdonordeste.raizesnovoapi.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raizesdonordeste.raizesnovoapi.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
