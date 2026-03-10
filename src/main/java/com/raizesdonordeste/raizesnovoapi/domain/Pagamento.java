package com.raizesdonordeste.raizesnovoapi.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private ResultadoPagamento resultadoPagamento;

    private LocalDateTime processadoEm;

    public Long getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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