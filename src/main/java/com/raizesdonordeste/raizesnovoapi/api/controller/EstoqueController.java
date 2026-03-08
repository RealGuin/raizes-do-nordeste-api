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

import com.raizesdonordeste.raizesnovoapi.api.dto.EstoqueRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.EstoqueResponse;
import com.raizesdonordeste.raizesnovoapi.application.service.EstoqueService;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueResponse criar(@RequestBody EstoqueRequest request) {
        return estoqueService.salvar(request);
    }

    @GetMapping
    public List<EstoqueResponse> listar() {
        return estoqueService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> buscarPorId(@PathVariable Long id) {
        EstoqueResponse estoque = estoqueService.buscarPorId(id);

        if (estoque == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estoque);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        estoqueService.deletarPorId(id);
    }
}
