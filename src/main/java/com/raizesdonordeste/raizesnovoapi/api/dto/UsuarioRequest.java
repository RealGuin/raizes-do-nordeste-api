package com.raizesdonordeste.raizesnovoapi.api.dto;

import com.raizesdonordeste.raizesnovoapi.domain.CanalPedido;
import com.raizesdonordeste.raizesnovoapi.domain.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UsuarioRequest {
	
	@NotBlank(message = "Nome é obrigatório")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome deve conter apenas letras")
    private String nome;
    
	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	private String email;
    
	@NotBlank(message = "Senha é obrigatória")
	private String senhaHash;
    
	@NotNull(message = "Role é obrigatória")
	private Role role;
    
	private boolean consentimentoLgpd;
	private CanalPedido canalPedido;
    
    public UsuarioRequest() {
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
    
    public CanalPedido getCanalPedido() {
        return canalPedido;
    }

    public void setCanalPedido(CanalPedido canalPedido) {
        this.canalPedido = canalPedido;
    }
    
}
