package com.raizesdonordeste.raizesnovoapi.domain.exception;

public class ValidacaoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    private final String instance;

    public ValidacaoException(String mensagem, String instance) {
        super(mensagem);
        this.instance = instance;
    }

    public String getInstance() {
        return instance;
    }
}