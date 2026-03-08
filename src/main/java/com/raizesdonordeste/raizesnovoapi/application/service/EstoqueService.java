package com.raizesdonordeste.raizesnovoapi.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.EstoqueRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.EstoqueResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.ProdutoResponse;
import com.raizesdonordeste.raizesnovoapi.domain.Estoque;
import com.raizesdonordeste.raizesnovoapi.domain.Unidade;
import com.raizesdonordeste.raizesnovoapi.domain.exception.ValidacaoException;
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
    public PaginacaoResponse<EstoqueResponse> listar(Pageable paginacao) {

        Page <Estoque> paginaEstoque = estoqueRepository.findAll(paginacao);
        
        List<EstoqueResponse> itens = paginaEstoque.getContent().stream()
        		.map(estoque -> {
		            EstoqueResponse response = new EstoqueResponse();
		            response.setId(estoque.getId());
		            response.setUnidadeId(estoque.getUnidade().getId());
		            response.setProdutoId(estoque.getProduto().getId());
		            response.setQuantidade(estoque.getQuantidade());
		            return response;
                })
        		.toList();
        
        PaginacaoResponse<EstoqueResponse> response = new PaginacaoResponse<>();
        response.setItens(itens);
        response.setPagina(paginaEstoque.getNumber());
        response.setTotalPaginas(paginaEstoque.getTotalPages());
        response.setTotalItens(paginaEstoque.getTotalElements());

        return response;
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
