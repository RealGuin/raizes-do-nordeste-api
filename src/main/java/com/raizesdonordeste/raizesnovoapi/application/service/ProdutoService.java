package com.raizesdonordeste.raizesnovoapi.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.domain.Produto;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void deletarPorId(Long id) {
        produtoRepository.deleteById(id);
    }
}
