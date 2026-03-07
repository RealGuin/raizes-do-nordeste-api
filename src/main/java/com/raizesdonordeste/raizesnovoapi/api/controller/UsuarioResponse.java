package com.raizesdonordeste.raizesnovoapi.api.controller;

import java.time.LocalDateTime;

public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String role;
    private boolean consentimentoLgpd;
    private LocalDateTime dataConsentimento;

    public UsuarioResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isConsentimentoLgpd() {
        return consentimentoLgpd;
    }

    public void setConsentimentoLgpd(boolean consentimentoLgpd) {
        this.consentimentoLgpd = consentimentoLgpd;
    }

    public LocalDateTime getDataConsentimento() {
        return dataConsentimento;
    }

    public void setDataConsentimento(LocalDateTime dataConsentimento) {
        this.dataConsentimento = dataConsentimento;
    }
}