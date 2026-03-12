package com.raizesdonordeste.raizesnovoapi.api.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.raizesdonordeste.raizesnovoapi.api.dto.ItemPedidoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.ItemPedidoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.application.service.ItemPedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemPedidoResponse criar(@Valid @RequestBody ItemPedidoRequest request) {
        return itemPedidoService.salvar(request);
    }

    @GetMapping
    public PaginacaoResponse<ItemPedidoResponse> listar(Pageable paginacao) {
        return itemPedidoService.listar(paginacao);
    }
    
    @GetMapping("/{id}")
    public ItemPedidoResponse buscarPorId(@PathVariable Long id) {
    	return itemPedidoService.buscarPorId(id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        itemPedidoService.deletarPorId(id);
    }
}
