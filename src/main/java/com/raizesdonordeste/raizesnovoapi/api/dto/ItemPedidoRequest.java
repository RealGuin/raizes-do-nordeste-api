package com.raizesdonordeste.raizesnovoapi.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemPedidoRequest {
	
	@NotNull(message = "pedidoId é obrigatório")
	@Positive(message = "pedidoId deve ser maior que zero")
    private Long pedidoId;
    
	@NotNull(message = "produtoId é obrigatório")
	@Positive(message = "produtoId deve ser maior que zero")
	private Long produtoId;
	
	@NotNull(message = "quantidade é obrigatório")
	@Positive(message = "quantidade deve ser maior que zero")
    private Integer quantidade;

    public ItemPedidoRequest() {
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
