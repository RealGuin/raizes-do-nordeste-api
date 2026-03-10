package com.raizesdonordeste.raizesnovoapi.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.raizesdonordeste.raizesnovoapi.domain.CanalPedido;
import com.raizesdonordeste.raizesnovoapi.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	Page<Pedido> findByCanalPedido(CanalPedido canalPedido, Pageable paginacao);

}
