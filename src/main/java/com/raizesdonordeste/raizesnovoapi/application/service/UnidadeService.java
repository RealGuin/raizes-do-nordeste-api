package com.raizesdonordeste.raizesnovoapi.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.UnidadeRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.UnidadeResponse;
import com.raizesdonordeste.raizesnovoapi.domain.Unidade;
import com.raizesdonordeste.raizesnovoapi.domain.exception.ConflitoException;
import com.raizesdonordeste.raizesnovoapi.domain.exception.RecursoNaoEncontradoException;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.UnidadeRepository;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public UnidadeResponse salvar(UnidadeRequest request) {
        
    	if (unidadeRepository.existsByNome(request.getNome())) {
    	    throw new ConflitoException("Já existe uma unidade cadastrada com esse nome.");
    	}
    	
    	Unidade unidade = new Unidade();
        unidade.setNome(request.getNome());
        unidade.setAtiva(request.isAtiva());

        Unidade unidadeSalva = unidadeRepository.save(unidade);

        UnidadeResponse response = new UnidadeResponse();
        response.setId(unidadeSalva.getId());
        response.setNome(unidadeSalva.getNome());
        response.setAtiva(unidadeSalva.isAtiva());

        return response;
    }

    public PaginacaoResponse<UnidadeResponse> listar(Pageable paginacao) {

        Page<Unidade> paginaUnidades = unidadeRepository.findAll(paginacao);

        List<UnidadeResponse> itens = paginaUnidades.getContent().stream()
                .map(unidade -> {
                    UnidadeResponse response = new UnidadeResponse();
                    response.setId(unidade.getId());
                    response.setNome(unidade.getNome());
                    response.setAtiva(unidade.isAtiva());
                    return response;
                })
                .toList();

        PaginacaoResponse<UnidadeResponse> response = new PaginacaoResponse<>();
        response.setItens(itens);
        response.setPagina(paginaUnidades.getNumber());
        response.setTotalPaginas(paginaUnidades.getTotalPages());
        response.setTotalItens(paginaUnidades.getTotalElements());

        return response;
    }

    public UnidadeResponse buscarPorId(Long id) {
        Unidade unidade = unidadeRepository.findById(id)
        		.orElseThrow(() -> new RecursoNaoEncontradoException("Unidade não encontrada."));

        UnidadeResponse response = new UnidadeResponse();
        response.setId(unidade.getId());
        response.setNome(unidade.getNome());
        response.setAtiva(unidade.isAtiva());

        return response;
    }

    public void deletarPorId(Long id) {
        unidadeRepository.deleteById(id);
    }
}