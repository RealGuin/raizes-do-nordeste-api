package com.raizesdonordeste.raizesnovoapi.api.dto;

import java.time.LocalDateTime;

import com.raizesdonordeste.raizesnovoapi.domain.ResultadoPagamento;

public class PagamentoResponse {

    private Long id;
    private Long pedidoId;
    private ResultadoPagamento resultadoPagamento;
    private LocalDateTime processadoEm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getProcessadoEm() {
        return processadoEm;
    }

    public void setProcessadoEm(LocalDateTime processadoEm) {
        this.processadoEm = processadoEm;
    }

}
