package com.raizesdonordeste.raizesnovoapi.application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.ItemPedidoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.ItemPedidoResponse;
import com.raizesdonordeste.raizesnovoapi.domain.ItemPedido;
import com.raizesdonordeste.raizesnovoapi.domain.Pedido;
import com.raizesdonordeste.raizesnovoapi.domain.Produto;
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

        Pedido pedido = pedidoRepository.findById(request.getPedidoId()).orElse(null);
        Produto produto = produtoRepository.findById(request.getProdutoId()).orElse(null);

        if (pedido == null || produto == null) {
            return null;
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

    public List<ItemPedidoResponse> listarTodos() {
        return itemPedidoRepository.findAll().stream().map(item -> {
            ItemPedidoResponse r = new ItemPedidoResponse();
            r.setId(item.getId());
            r.setPedidoId(item.getPedido().getId());
            r.setProdutoId(item.getProduto().getId());
            r.setQuantidade(item.getQuantidade());
            r.setPrecoUnitario(item.getPrecoUnitario());
            r.setSubtotal(item.getSubtotal());
            return r;
        }).toList();
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
