package com.raizesdonordeste.raizesnovoapi.api.dto;

import com.raizesdonordeste.raizesnovoapi.domain.Role;

public class LoginResponse {

	private Long id;
    private String token;
    private String tipo;
    private Role role;
    
    
    public Long getId() {
  		return id;
  	}

  	public void setId(Long id) {
  		this.id = id;
  	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
}
