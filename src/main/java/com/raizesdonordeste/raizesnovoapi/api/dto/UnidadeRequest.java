package com.raizesdonordeste.raizesnovoapi.api.dto;

public class UnidadeRequest {

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
