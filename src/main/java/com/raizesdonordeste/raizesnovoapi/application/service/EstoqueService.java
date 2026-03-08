package com.raizesdonordeste.raizesnovoapi.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.EstoqueRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.EstoqueResponse;
import com.raizesdonordeste.raizesnovoapi.domain.Estoque;
import com.raizesdonordeste.raizesnovoapi.domain.Unidade;
import com.raizesdonordeste.raizesnovoapi.domain.Produto;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.EstoqueRepository;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.UnidadeRepository;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.ProdutoRepository;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final UnidadeRepository unidadeRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(
            EstoqueRepository estoqueRepository,
            UnidadeRepository unidadeRepository,
            ProdutoRepository produtoRepository) {

        this.estoqueRepository = estoqueRepository;
        this.unidadeRepository = unidadeRepository;
        this.produtoRepository = produtoRepository;
    }

    public EstoqueResponse salvar(EstoqueRequest request) {

        Unidade unidade = unidadeRepository.findById(request.getUnidadeId()).orElse(null);
        Produto produto = produtoRepository.findById(request.getProdutoId()).orElse(null);

        if (unidade == null || produto == null) {
            return null;
        }

        Estoque estoque = estoqueRepository.findByUnidadeIdAndProdutoId(
                request.getUnidadeId(),
                request.getProdutoId()
        );

        if (estoque == null) {
            estoque = new Estoque();
            estoque.setUnidade(unidade);
            estoque.setProduto(produto);
            estoque.setQuantidade(request.getQuantidade());
        } else {
            estoque.setQuantidade(estoque.getQuantidade() + request.getQuantidade());
        }

        Estoque salvo = estoqueRepository.save(estoque);

        EstoqueResponse response = new EstoqueResponse();
        response.setId(salvo.getId());
        response.setUnidadeId(salvo.getUnidade().getId());
        response.setProdutoId(salvo.getProduto().getId());
        response.setQuantidade(salvo.getQuantidade());

        return response;
    }

    public List<EstoqueResponse> listarTodos() {
        return estoqueRepository.findAll().stream().map(estoque -> {
            EstoqueResponse r = new EstoqueResponse();
            r.setId(estoque.getId());
            r.setUnidadeId(estoque.getUnidade().getId());
            r.setProdutoId(estoque.getProduto().getId());
            r.setQuantidade(estoque.getQuantidade());
            return r;
        }).toList();
    }

    public EstoqueResponse buscarPorId(Long id) {

        Estoque estoque = estoqueRepository.findById(id).orElse(null);

        if (estoque == null) {
            return null;
        }

        EstoqueResponse response = new EstoqueResponse();
        response.setId(estoque.getId());
        response.setUnidadeId(estoque.getUnidade().getId());
        response.setProdutoId(estoque.getProduto().getId());
        response.setQuantidade(estoque.getQuantidade());

        return response;
    }

    public void deletarPorId(Long id) {
        estoqueRepository.deleteById(id);
    }
}
