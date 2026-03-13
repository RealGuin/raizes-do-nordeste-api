package com.raizesdonordeste.raizesnovoapi.application.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raizesdonordeste.raizesnovoapi.api.dto.ItemPedidoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.PedidoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.PedidoResponse;
import com.raizesdonordeste.raizesnovoapi.domain.CanalPedido;
import com.raizesdonordeste.raizesnovoapi.domain.Pedido;
import com.raizesdonordeste.raizesnovoapi.domain.StatusPedido;
import com.raizesdonordeste.raizesnovoapi.domain.Unidade;
import com.raizesdonordeste.raizesnovoapi.domain.Usuario;
import com.raizesdonordeste.raizesnovoapi.domain.exception.ConflitoException;
import com.raizesdonordeste.raizesnovoapi.domain.exception.RecursoNaoEncontradoException;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.PedidoRepository;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.UnidadeRepository;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.UsuarioRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UnidadeRepository unidadeRepository;
    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    public PedidoService(PedidoRepository pedidoRepository,
                         UsuarioRepository usuarioRepository,
                         UnidadeRepository unidadeRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.unidadeRepository = unidadeRepository;
    }

    public PedidoResponse salvar(PedidoRequest request) {
    	
        Usuario cliente = usuarioRepository.findById(request.getClienteId()).orElse(null);
        Unidade unidade = unidadeRepository.findById(request.getUnidadeId()).orElse(null);

        if (cliente == null) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado.");
        }

        if (unidade == null) {
            throw new RecursoNaoEncontradoException("Unidade não encontrada.");
        }
        
        if (!unidade.isAtiva()) {
            throw new ConflitoException("Unidade inativa não pode receber pedidos.");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setUnidade(unidade);
        pedido.setCanalPedido(request.getCanalPedido());
        pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setValorTotal(BigDecimal.ZERO);
        pedido.setCriadoEm(LocalDateTime.now());
        pedido.setCpfNota(request.getCpfNota());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        
        log.info("Pedido criado - id={}, clienteId={}, canal={}, valorTotal={}",
                pedidoSalvo.getId(),
                cliente.getId(),
                pedidoSalvo.getCanalPedido(),
                pedidoSalvo.getValorTotal());
        
        return toResponse(pedidoSalvo);
    	
    }

    public PaginacaoResponse<PedidoResponse> listar(Pageable paginacao, CanalPedido canalPedido) {

        Page<Pedido> paginaPedidos;

        if (canalPedido != null) {
            paginaPedidos = pedidoRepository.findByCanalPedido(canalPedido, paginacao);
        } else {
            paginaPedidos = pedidoRepository.findAll(paginacao);
        }

        List<PedidoResponse> itens = paginaPedidos.getContent().stream()
                .map(this::toResponse)
                .toList();

        PaginacaoResponse<PedidoResponse> response = new PaginacaoResponse<>();
        response.setItens(itens);
        response.setPagina(paginaPedidos.getNumber());
        response.setTotalPaginas(paginaPedidos.getTotalPages());
        response.setTotalItens(paginaPedidos.getTotalElements());

        return response;
    }

    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
        		.orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado."));
        
        return toResponse(pedido);
    }

    public void deletarPorId(Long id) {
        pedidoRepository.deleteById(id);
    }

    private PedidoResponse toResponse(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();
        response.setId(pedido.getId());
        response.setClienteId(pedido.getCliente().getId());
        response.setUnidadeId(pedido.getUnidade().getId());
        response.setCanalPedido(pedido.getCanalPedido());
        response.setStatusPedido(pedido.getStatusPedido());
        response.setValorTotal(pedido.getValorTotal());
        response.setCriadoEm(pedido.getCriadoEm());
        response.setCpfNota(pedido.getCpfNota());
        
        if (pedido.getItens() != null) {
            response.setItens(
                pedido.getItens().stream().map(item -> {
                    ItemPedidoResponse itemResponse = new ItemPedidoResponse();
                    itemResponse.setId(item.getId());
                    itemResponse.setPedidoId(item.getPedido().getId());
                    itemResponse.setProdutoId(item.getProduto().getId());
                    itemResponse.setQuantidade(item.getQuantidade());
                    itemResponse.setPrecoUnitario(item.getPrecoUnitario());
                    itemResponse.setSubtotal(item.getSubtotal());
                    return itemResponse;
                }).toList()
            );
        }
        
        return response;
    }
}
