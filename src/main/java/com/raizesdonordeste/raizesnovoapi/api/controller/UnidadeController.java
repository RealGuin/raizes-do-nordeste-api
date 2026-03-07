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

import com.raizesdonordeste.raizesnovoapi.api.dto.UnidadeRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.UnidadeResponse;
import com.raizesdonordeste.raizesnovoapi.application.service.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnidadeResponse criar(@RequestBody UnidadeRequest request) {
        return unidadeService.salvar(request);
    }

    @GetMapping
    public List<UnidadeResponse> listar() {
        return unidadeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponse> buscarPorId(@PathVariable Long id) {
        UnidadeResponse unidade = unidadeService.buscarPorId(id);

        if (unidade == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(unidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        unidadeService.deletarPorId(id);
    }
}
