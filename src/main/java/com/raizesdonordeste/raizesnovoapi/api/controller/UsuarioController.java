package com.raizesdonordeste.raizesnovoapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.raizesdonordeste.raizesnovoapi.api.dto.UsuarioRequest;
import com.raizesdonordeste.raizesnovoapi.application.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse criar(@RequestBody UsuarioRequest request) {
        return usuarioService.salvar(request);
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public UsuarioResponse buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        usuarioService.deletarPorId(id);
    }
}
