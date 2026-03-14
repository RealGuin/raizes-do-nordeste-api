package com.raizesdonordeste.raizesnovoapi.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raizesdonordeste.raizesnovoapi.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
	
	List<ItemPedido> findByPedidoId(Long pedidoId);

}
