package com.raizesdonordeste.raizesnovoapi.api.dto;

import com.raizesdonordeste.raizesnovoapi.domain.ResultadoPagamento;

public class PagamentoRequest {

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