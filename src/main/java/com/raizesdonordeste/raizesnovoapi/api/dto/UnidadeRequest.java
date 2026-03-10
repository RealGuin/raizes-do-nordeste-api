package com.raizesdonordeste.raizesnovoapi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UnidadeRequest {

	@NotBlank(message = "Nome é obrigatório")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome deve conter apenas letras")
	private String nome;
	
    private boolean ativa;

    public UnidadeRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}
