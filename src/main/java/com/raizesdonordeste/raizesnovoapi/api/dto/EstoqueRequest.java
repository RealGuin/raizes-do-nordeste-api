package com.raizesdonordeste.raizesnovoapi.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class EstoqueRequest {

	@NotNull(message = "unidadeId é obrigatório")
	private Long unidadeId;

	@NotNull(message = "produtoId é obrigatório")
	private Long produtoId;

	@NotNull(message = "quantidade é obrigatória")
	@Min(value = 1, message = "quantidade deve ser no mínimo 1")
	private Integer quantidade;

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
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
