package com.raizesdonordeste.raizesnovoapi.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.PagamentoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.PagamentoResponse;
import com.raizesdonordeste.raizesnovoapi.domain.Pagamento;
import com.raizesdonordeste.raizesnovoapi.domain.Pedido;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.PagamentoRepository;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.PedidoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public PagamentoResponse salvar(PagamentoRequest request) {

        Pedido pedido = pedidoRepository.findById(request.getPedidoId()).orElse(null);

        if (pedido == null) {
            return null;
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setResultadoPagamento(request.getResultadoPagamento());
        pagamento.setProcessadoEm(LocalDateTime.now());

        Pagamento salvo = pagamentoRepository.save(pagamento);

        PagamentoResponse response = new PagamentoResponse();
        response.setId(salvo.getId());
        response.setPedidoId(pedido.getId());
        response.setResultadoPagamento(salvo.getResultadoPagamento());
        response.setProcessadoEm(salvo.getProcessadoEm());

        return response;
    }

    public List<PagamentoResponse> listarTodos() {
        return pagamentoRepository.findAll().stream().map(pagamento -> {
            PagamentoResponse r = new PagamentoResponse();
            r.setId(pagamento.getId());
            r.setPedidoId(pagamento.getPedido().getId());
            r.setResultadoPagamento(pagamento.getResultadoPagamento());
            r.setProcessadoEm(pagamento.getProcessadoEm());
            return r;
        }).toList();
    }

    public PagamentoResponse buscarPorId(Long id) {

        Pagamento pagamento = pagamentoRepository.findById(id).orElse(null);

        if (pagamento == null) {
            return null;
        }

        PagamentoResponse response = new PagamentoResponse();
        response.setId(pagamento.getId());
        response.setPedidoId(pagamento.getPedido().getId());
        response.setResultadoPagamento(pagamento.getResultadoPagamento());
        response.setProcessadoEm(pagamento.getProcessadoEm());

        return response;
    }

    public void deletarPorId(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
