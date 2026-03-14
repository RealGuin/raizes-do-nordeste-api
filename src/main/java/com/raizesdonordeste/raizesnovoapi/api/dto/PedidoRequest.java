package com.raizesdonordeste.raizesnovoapi.api.dto;

import com.raizesdonordeste.raizesnovoapi.domain.CanalPedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class PedidoRequest {

	@NotNull(message = "clienteId é obrigatório")
	@Positive(message = "clienteId deve ser maior que zero")
	private Long clienteId;
	
	@NotNull(message = "unidadeId é obrigatório")
	@Positive(message = "unidadeId deve ser maior que zero")
	private Long unidadeId;
	
	@NotNull(message = "canalPedido é obrigatório")
	private CanalPedido canalPedido;
    
	@Pattern(regexp = "\\d{11}", message = "cpfNota deve conter 11 números")
	private String cpfNota;

    public PedidoRequest() {
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public CanalPedido getCanalPedido() {
        return canalPedido;
    }

    public void setCanalPedido(CanalPedido canalPedido) {
        this.canalPedido = canalPedido;
    }

    public String getCpfNota() {
        return cpfNota;
    }

    public void setCpfNota(String cpfNota) {
        this.cpfNota = cpfNota;
    }
}
