package com.raizesdonordeste.raizesnovoapi.api.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class ProdutoRequest {
	
	@NotBlank(message = "Nome do produto é obrigatório")
	@Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome deve conter apenas letras")
    private String nome;
    
	@NotNull(message = "precoBase é obrigatório")
	@Positive(message = "Preço deve ser maior que zero")
	private BigDecimal precoBase;
    
	private boolean produtoAtivo;
    private boolean promocaoAtiva;
    
    @NotNull(message = "descontoPercentual é obrigatório")
    @Range(min = 0, max = 10, message = "descontoPercentual deve estar entre 0 e 10")
    private Integer descontoPercentual;

    public ProdutoRequest() {}

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
