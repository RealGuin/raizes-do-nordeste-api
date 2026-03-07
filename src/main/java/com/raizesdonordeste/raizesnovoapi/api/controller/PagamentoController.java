package com.raizesdonordeste.raizesnovoapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.raizesdonordeste.raizesnovoapi.api.dto.PagamentoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.PagamentoResponse;
import com.raizesdonordeste.raizesnovoapi.application.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagamentoResponse criar(@RequestBody PagamentoRequest request) {
        return pagamentoService.salvar(request);
    }

    @GetMapping
    public List<PagamentoResponse> listar() {
        return pagamentoService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> buscarPorId(@PathVariable Long id) {

        PagamentoResponse pagamento = pagamentoService.buscarPorId(id);

        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        pagamentoService.deletarPorId(id);
    }
}
