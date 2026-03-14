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

import com.raizesdonordeste.raizesnovoapi.api.dto.EstoqueRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.EstoqueResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.application.service.EstoqueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueResponse criar(@Valid @RequestBody EstoqueRequest request) {
        return estoqueService.salvar(request);
    }

    @GetMapping
    public PaginacaoResponse<EstoqueResponse> listar(Pageable paginacao) {
        return estoqueService.listar(paginacao);
    }

    @GetMapping("/{id}")
    public EstoqueResponse buscarPorId(@PathVariable Long id) {
        return estoqueService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        estoqueService.deletarPorId(id);
    }
}
