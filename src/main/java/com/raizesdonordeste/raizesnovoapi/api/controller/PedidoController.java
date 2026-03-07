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

import com.raizesdonordeste.raizesnovoapi.api.dto.PedidoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.PedidoResponse;
import com.raizesdonordeste.raizesnovoapi.application.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse criar(@RequestBody PedidoRequest request) {
        return pedidoService.salvar(request);
    }

    @GetMapping
    public List<PedidoResponse> listar() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id) {
        PedidoResponse pedido = pedidoService.buscarPorId(id);

        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        pedidoService.deletarPorId(id);
    }
}
