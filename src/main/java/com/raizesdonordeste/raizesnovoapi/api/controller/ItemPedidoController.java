package com.raizesdonordeste.raizesnovoapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.raizesdonordeste.raizesnovoapi.application.service.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemPedidoResponse criar(@RequestBody ItemPedidoRequest request) {
        return itemPedidoService.salvar(request);
    }

    @GetMapping
    public List<ItemPedidoResponse> listar() {
        return itemPedidoService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoResponse> buscarPorId(@PathVariable Long id) {

        ItemPedidoResponse item = itemPedidoService.buscarPorId(id);

        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(item);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        itemPedidoService.deletarPorId(id);
    }
}
