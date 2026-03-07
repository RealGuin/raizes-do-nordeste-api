package com.raizesdonordeste.raizesnovoapi.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raizesdonordeste.raizesnovoapi.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
