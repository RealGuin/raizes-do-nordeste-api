package com.raizesdonordeste.raizesnovoapi.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.UnidadeRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.UnidadeResponse;
import com.raizesdonordeste.raizesnovoapi.domain.Unidade;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.UnidadeRepository;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public UnidadeResponse salvar(UnidadeRequest request) {
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

    public List<UnidadeResponse> listarTodos() {
        List<Unidade> unidades = unidadeRepository.findAll();

        return unidades.stream().map(unidade -> {
            UnidadeResponse response = new UnidadeResponse();
            response.setId(unidade.getId());
            response.setNome(unidade.getNome());
            response.setAtiva(unidade.isAtiva());
            return response;
        }).toList();
    }

    public UnidadeResponse buscarPorId(Long id) {
        Unidade unidade = unidadeRepository.findById(id).orElse(null);

        if (unidade == null) {
            return null;
        }

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