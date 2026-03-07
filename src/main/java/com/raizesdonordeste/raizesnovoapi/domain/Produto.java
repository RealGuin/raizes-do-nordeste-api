package com.raizesdonordeste.raizesnovoapi.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal precoBase;

    private boolean produtoAtivo;

    private boolean promocaoAtiva;

    private int descontoPercentual;

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public boolean isProdutoAtivo() {
        return produtoAtivo;
    }

    public void setProdutoAtivo(boolean produtoAtivo) {
        this.produtoAtivo = produtoAtivo;
    }

    public boolean isPromocaoAtiva() {
        return promocaoAtiva;
    }

    public void setPromocaoAtiva(boolean promocaoAtiva) {
        this.promocaoAtiva = promocaoAtiva;
    }

    public int getDescontoPercentual() {
        return descontoPercentual;
    }

    public void setDescontoPercentual(int descontoPercentual) {
        this.descontoPercentual = descontoPercentual;
    }

    public BigDecimal precoFinalPromocao() {
        if (!promocaoAtiva) {
            return precoBase;
        }

        BigDecimal desconto = precoBase
                .multiply(BigDecimal.valueOf(descontoPercentual))
                .divide(BigDecimal.valueOf(100));

        return precoBase.subtract(desconto);
    }
}
