package com.raizesdonordeste.raizesnovoapi.infrastructure.mock;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.raizesdonordeste.raizesnovoapi.domain.ResultadoPagamento;

@Component
public class MockPagamentoGateway {

    public ResultadoPagamento processarPagamento() {

        Random random = new Random();
        int numero = random.nextInt(10);
        
        // 70%
        if (numero < 7) {
            return ResultadoPagamento.APROVADO;
        }

        // 20%
        if (numero < 9) {
            return ResultadoPagamento.RECUSADO;
        }
        
        //10%
        return ResultadoPagamento.ERRO;  
    }
}
