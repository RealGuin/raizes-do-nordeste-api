package com.raizesdonordeste.raizesnovoapi.application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.ItemPedidoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.ItemPedidoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.domain.ItemPedido;
import com.raizesdonordeste.raizesnovoapi.domain.Pedido;
import com.raizesdonordeste.raizesnovoapi.domain.Produto;
import com.raizesdonordeste.raizesnovoapi.domain.exception.RecursoNaoEncontradoException;
import com.raizesdonordeste.raizesnovoapi.domain.exception.ValidacaoException;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.ItemPedidoRepository;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.PedidoRepository;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository,
                             PedidoRepository pedidoRepository,
                             ProdutoRepository produtoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public ItemPedidoResponse salvar(ItemPedidoRequest request) {
    	
    	if (request.getQuantidade() == null || request.getQuantidade() <= 0) {
    	    throw new ValidacaoException("Quantidade deve ser maior que zero");
    	}

        Pedido pedido = pedidoRepository.findById(request.getPedidoId()).orElse(null);
        Produto produto = produtoRepository.findById(request.getProdutoId()).orElse(null);

        if (pedido == null) {
            throw new RecursoNaoEncontradoException("Pedido não encontrado.");
        }

        if (produto == null) {
            throw new RecursoNaoEncontradoException("Produto não encontrado.");
        }

        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(request.getQuantidade());

        BigDecimal precoUnitario = produto.getPrecoBase();
        item.setPrecoUnitario(precoUnitario);

        BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(request.getQuantidade()));
        item.setSubtotal(subtotal);

        ItemPedido salvo = itemPedidoRepository.save(item);

        ItemPedidoResponse response = new ItemPedidoResponse();
        response.setId(salvo.getId());
        response.setPedidoId(pedido.getId());
        response.setProdutoId(produto.getId());
        response.setQuantidade(salvo.getQuantidade());
        response.setPrecoUnitario(salvo.getPrecoUnitario());
        response.setSubtotal(salvo.getSubtotal());

        return response;
    }

    public PaginacaoResponse<ItemPedidoResponse> listar(Pageable paginacao) {

        Page<ItemPedido> paginaItens = itemPedidoRepository.findAll(paginacao);

        List<ItemPedidoResponse> itens = paginaItens.getContent().stream()
                .map(item -> {
                    ItemPedidoResponse response = new ItemPedidoResponse();
                    response.setId(item.getId());
                    response.setPedidoId(item.getPedido().getId());
                    response.setProdutoId(item.getProduto().getId());
                    response.setQuantidade(item.getQuantidade());
                    response.setPrecoUnitario(item.getPrecoUnitario());
                    response.setSubtotal(item.getSubtotal());
                    return response;
                })
                .toList();

        PaginacaoResponse<ItemPedidoResponse> response = new PaginacaoResponse<>();
        response.setItens(itens);
        response.setPagina(paginaItens.getNumber());
        response.setTotalPaginas(paginaItens.getTotalPages());
        response.setTotalItens(paginaItens.getTotalElements());

        return response;
    }
    
    public ItemPedidoResponse buscarPorId(Long id) {
    	
        ItemPedido item = itemPedidoRepository.findById(id).orElse(null);

        if (item == null) {
            return null;
        }

        ItemPedidoResponse response = new ItemPedidoResponse();
        response.setId(item.getId());
        response.setPedidoId(item.getPedido().getId());
        response.setProdutoId(item.getProduto().getId());
        response.setQuantidade(item.getQuantidade());
        response.setPrecoUnitario(item.getPrecoUnitario());
        response.setSubtotal(item.getSubtotal());

        return response;
    }
    
    public void deletarPorId(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
