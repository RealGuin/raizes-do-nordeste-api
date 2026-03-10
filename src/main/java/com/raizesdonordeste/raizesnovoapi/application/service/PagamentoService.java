package com.raizesdonordeste.raizesnovoapi.application.service;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raizesdonordeste.raizesnovoapi.api.dto.PagamentoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.PagamentoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.domain.Pagamento;
import com.raizesdonordeste.raizesnovoapi.domain.Pedido;
import com.raizesdonordeste.raizesnovoapi.domain.ResultadoPagamento;
import com.raizesdonordeste.raizesnovoapi.domain.StatusPedido;
import com.raizesdonordeste.raizesnovoapi.domain.exception.ConflictException;
import com.raizesdonordeste.raizesnovoapi.domain.exception.RecursoNaoEncontradoException;
import com.raizesdonordeste.raizesnovoapi.infrastructure.mock.MockPagamentoGateway;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.PagamentoRepository;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.PedidoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;
    private final MockPagamentoGateway mockPagamentoGateway;
    private static final Logger log = LoggerFactory.getLogger(PagamentoService.class);

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            PedidoRepository pedidoRepository,
                            MockPagamentoGateway mockPagamentoGateway) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
        this.mockPagamentoGateway = mockPagamentoGateway;
    }

    public PagamentoResponse salvar(PagamentoRequest request) {
    	
    	
        Pedido pedido = pedidoRepository.findById(request.getPedidoId()).orElse(null);
        

        if (pedido == null) {
            throw new RecursoNaoEncontradoException("Pedido não encontrado");
        }
        
        if (pedido.getStatusPedido() == StatusPedido.PAGO) {
        	throw new ConflictException(
                "Este pedido já foi pago"
            );
        }
        
        // Mock decide o resultado
        ResultadoPagamento resultado = mockPagamentoGateway.processarPagamento();

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setResultadoPagamento(resultado);
        pagamento.setProcessadoEm(LocalDateTime.now());

        // Atualiza status do pedido se aprovado
        if (resultado == ResultadoPagamento.APROVADO) {
            pedido.setStatusPedido(StatusPedido.PAGO);
            pedidoRepository.save(pedido);
        }

        Pagamento salvo = pagamentoRepository.save(pagamento);
        
        log.info("Pagamento processado - pedidoId={}, resultado={}",
                pagamento.getPedido().getId(),
                pagamento.getResultadoPagamento());

        PagamentoResponse response = new PagamentoResponse();
        response.setId(salvo.getId());
        response.setPedidoId(pedido.getId());
        response.setResultadoPagamento(salvo.getResultadoPagamento());
        response.setProcessadoEm(salvo.getProcessadoEm());

        return response;
    }

    public PaginacaoResponse<PagamentoResponse> listar(Pageable paginacao) {

        Page<Pagamento> paginaPagamentos = pagamentoRepository.findAll(paginacao);

        List<PagamentoResponse> itens = paginaPagamentos.getContent().stream()
                .map(pagamento -> {
                    PagamentoResponse response = new PagamentoResponse();
                    response.setId(pagamento.getId());
                    response.setPedidoId(pagamento.getPedido().getId());
                    response.setResultadoPagamento(pagamento.getResultadoPagamento());
                    response.setProcessadoEm(pagamento.getProcessadoEm());
                    return response;
                })
                .toList();

        PaginacaoResponse<PagamentoResponse> response = new PaginacaoResponse<>();
        response.setItens(itens);
        response.setPagina(paginaPagamentos.getNumber());
        response.setTotalPaginas(paginaPagamentos.getTotalPages());
        response.setTotalItens(paginaPagamentos.getTotalElements());

        return response;
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
