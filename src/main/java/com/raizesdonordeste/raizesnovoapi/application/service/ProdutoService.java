package com.raizesdonordeste.raizesnovoapi.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.ProdutoRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.ProdutoResponse;
import com.raizesdonordeste.raizesnovoapi.domain.Produto;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponse salvar(ProdutoRequest request) {	
    	
    	Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setPrecoBase(request.getPrecoBase());
        produto.setProdutoAtivo(request.isProdutoAtivo());
        produto.setPromocaoAtiva(request.isPromocaoAtiva());
        produto.setDescontoPercentual(request.getDescontoPercentual());

        Produto produtoSalvo = produtoRepository.save(produto);

        ProdutoResponse response = new ProdutoResponse();
        response.setId(produtoSalvo.getId());
        response.setNome(produtoSalvo.getNome());
        response.setPrecoBase(produtoSalvo.getPrecoBase());
        response.setProdutoAtivo(produtoSalvo.isProdutoAtivo());
        response.setPromocaoAtiva(produtoSalvo.isPromocaoAtiva());
        response.setDescontoPercentual(produtoSalvo.getDescontoPercentual());

        return response;
    }

    public PaginacaoResponse<ProdutoResponse> listar(Pageable paginacao) {

        Page<Produto> paginaProdutos = produtoRepository.findAll(paginacao);

        List<ProdutoResponse> itens = paginaProdutos.getContent().stream()
                .map(produto -> {
                    ProdutoResponse response = new ProdutoResponse();
                    response.setId(produto.getId());
                    response.setNome(produto.getNome());
                    response.setPrecoBase(produto.getPrecoBase());
                    response.setProdutoAtivo(produto.isProdutoAtivo());
                    response.setPromocaoAtiva(produto.isPromocaoAtiva());
                    response.setDescontoPercentual(produto.getDescontoPercentual());
                    return response;
                })
                .toList();

        PaginacaoResponse<ProdutoResponse> response = new PaginacaoResponse<>();
        response.setItens(itens);
        response.setPagina(paginaProdutos.getNumber());
        response.setTotalPaginas(paginaProdutos.getTotalPages());
        response.setTotalItens(paginaProdutos.getTotalElements());

        return response;
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);

        if (produto == null) {
            return null;
        }

        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setPrecoBase(produto.getPrecoBase());
        response.setProdutoAtivo(produto.isProdutoAtivo());
        response.setPromocaoAtiva(produto.isPromocaoAtiva());
        response.setDescontoPercentual(produto.getDescontoPercentual());

        return response;
    }

    public void deletarPorId(Long id) {
        produtoRepository.deleteById(id);
    }
}