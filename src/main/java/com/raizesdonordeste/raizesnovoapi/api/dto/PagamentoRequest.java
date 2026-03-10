package com.raizesdonordeste.raizesnovoapi.api.dto;

import com.raizesdonordeste.raizesnovoapi.domain.ResultadoPagamento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PagamentoRequest {

	@NotNull(message = "pedidoId é obrigatório")
	@Positive(message = "pedidoId deve ser maior que zero")
	private Long pedidoId;
    
	private ResultadoPagamento resultadoPagamento;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public ResultadoPagamento getResultadoPagamento() {
        return resultadoPagamento;
    }

    public void setResultadoPagamento(ResultadoPagamento resultadoPagamento) {
        this.resultadoPagamento = resultadoPagamento;
    }

}