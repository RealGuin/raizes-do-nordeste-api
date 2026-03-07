package com.raizesdonordeste.raizesnovoapi.api.dto;

import java.math.BigDecimal;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private BigDecimal precoBase;
    private boolean produtoAtivo;
    private boolean promocaoAtiva;
    private int descontoPercentual;

    public ProdutoResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getPrecoBase() { return precoBase; }
    public void setPrecoBase(BigDecimal precoBase) { this.precoBase = precoBase; }

    public boolean isProdutoAtivo() { return produtoAtivo; }
    public void setProdutoAtivo(boolean produtoAtivo) { this.produtoAtivo = produtoAtivo; }

    public boolean isPromocaoAtiva() { return promocaoAtiva; }
    public void setPromocaoAtiva(boolean promocaoAtiva) { this.promocaoAtiva = promocaoAtiva; }

    public int getDescontoPercentual() { return descontoPercentual; }
    public void setDescontoPercentual(int descontoPercentual) { this.descontoPercentual = descontoPercentual; }
}
