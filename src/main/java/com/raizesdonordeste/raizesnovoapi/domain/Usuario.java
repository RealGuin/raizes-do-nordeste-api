package com.raizesdonordeste.raizesnovoapi.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nome;

	    private String email;

	    private String senhaHash;

	    @Enumerated(EnumType.STRING)
	    private Role role;

	    private boolean consentimentoLgpd;

	    private LocalDateTime dataConsentimento;

	    public Usuario() {
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

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getSenhaHash() {
	        return senhaHash;
	    }

	    public void setSenhaHash(String senhaHash) {
	        this.senhaHash = senhaHash;
	    }

	    public Role getRole() {
	        return role;
	    }

	    public void setRole(Role role) {
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

}
