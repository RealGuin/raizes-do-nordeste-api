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

import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.ProdutoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.ProdutoResponse;
import com.raizesdonordeste.raizesnovoapi.application.service.ProdutoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse criar(@Valid @RequestBody ProdutoRequest request) {
        return produtoService.salvar(request);
    }

    @GetMapping
    public PaginacaoResponse<ProdutoResponse> listar(Pageable paginacao) {
        return produtoService.listar(paginacao);
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        produtoService.deletarPorId(id);
    }
}
